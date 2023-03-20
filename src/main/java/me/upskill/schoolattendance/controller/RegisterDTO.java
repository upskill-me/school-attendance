package me.upskill.schoolattendance.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDTO {

    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private String password;
}
