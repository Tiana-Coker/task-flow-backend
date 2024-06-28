package com.squad22podA.task_mgt.controller;

import com.squad22podA.task_mgt.service.TokenValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserEmailConfirmationController {

    private final TokenValidationService tokenValidationService;

    @GetMapping("/confirm")
    public ModelAndView confirmEmail(@RequestParam("token") String token){
        String result = tokenValidationService.validateToken(token);
        if ("Email confirmed successfully".equals(result)) {
            // Redirect to the confirmation success page
            return new ModelAndView("redirect:http://127.0.0.1:5500/confirmation/confirm-token-sucess.html");
        } else {
            // Handle invalid token or other errors
            return new ModelAndView("redirect:http://127.0.0.1:5500/confirmation/confirmation.html"); // Redirect to an error page
        }

    }

}
