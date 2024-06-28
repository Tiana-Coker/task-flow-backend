package com.squad22podA.task_mgt.service.impl;

import com.squad22podA.task_mgt.config.JwtService;
import com.squad22podA.task_mgt.entity.enums.Role;
import com.squad22podA.task_mgt.entity.enums.TokenType;
import com.squad22podA.task_mgt.entity.model.ConfirmationToken;
import com.squad22podA.task_mgt.entity.model.JToken;
import com.squad22podA.task_mgt.entity.model.UserModel;
import com.squad22podA.task_mgt.exception.EmailAlreadyExistException;
import com.squad22podA.task_mgt.payload.request.*;
import com.squad22podA.task_mgt.payload.response.LoginInfo;
import com.squad22podA.task_mgt.payload.response.LoginResponse;
import com.squad22podA.task_mgt.repository.ConfirmationTokenRepository;
import com.squad22podA.task_mgt.repository.JTokenRepository;
import com.squad22podA.task_mgt.repository.UserModelRepository;
import com.squad22podA.task_mgt.service.EmailService;
import com.squad22podA.task_mgt.service.UserModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserModelServiceImpl implements UserModelService {


    private final UserModelRepository userModelRepository;
    private final JTokenRepository jTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    @Override
    public void registerUser(UserRegistrationRequest registrationRequest) {

        if(!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords do not match!");
        }

        Optional<UserModel> existingUser = userModelRepository.findByEmail(registrationRequest.getEmail());

        if(existingUser.isPresent()){
            throw new EmailAlreadyExistException("Email already exists. Login to your account");
        }


        UserModel newUser = UserModel.builder().firstName(registrationRequest.getFirstName())
                                        .lastName(registrationRequest.getLastName())
                                        .email(registrationRequest.getEmail())
                                        .phoneNumber(registrationRequest.getPhoneNumber())
                                        .password(passwordEncoder.encode(registrationRequest.getPassword()))
                                        .role(Role.USER)
                                        .build();

        UserModel savedUser = userModelRepository.save(newUser);

        ConfirmationToken confirmationToken = new ConfirmationToken(savedUser);
        confirmationTokenRepository.save(confirmationToken);

        String confirmationUrl = "http://localhost:8080/api/auth/confirm?token=" + confirmationToken.getToken();

        //send email alert
        EmailDetails emailDetails = EmailDetails.builder()
                                    .recipient(savedUser.getEmail())
                                    .subject("ACCOUNT CREATION")
                                    .messageBody("CONGRATULATIONS!!! Your User Account Has Been Successfully Created.\n"
                                    + "Your Account Details: \n" + "Account FullName: " + savedUser.getFirstName() + " \n"
                                     + "Confirm your email " +
                                            "Please click the link to confirm your registration: " + confirmationUrl)
                                    .build();

        emailService.sendEmailAlert(emailDetails);

    }

    @Override
    public LoginResponse loginUser(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
        UserModel user = userModelRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return LoginResponse.builder()
                .responseCode("002")
                .responseMessage("Login Successfully")
                .loginInfo(LoginInfo.builder()
                        .email(user.getEmail())
                        .token(jwtToken)
                        .build())
                .build();
    }

    private void saveUserToken(UserModel userModel, String jwtToken) {
        var token = JToken.builder()
                .userModel(userModel)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        jTokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserModel userModel) {
        var validUserTokens = jTokenRepository.findAllValidTokenByUser(userModel.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        jTokenRepository.saveAll(validUserTokens);
    }



}
