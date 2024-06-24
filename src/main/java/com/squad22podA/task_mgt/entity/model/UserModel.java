package com.squad22podA.task_mgt.entity.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    private String firstName;

    private  String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();

}
