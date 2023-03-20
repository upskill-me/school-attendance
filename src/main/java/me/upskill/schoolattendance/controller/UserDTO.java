package me.upskill.schoolattendance.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {

    private long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private long createdAt;

    private long updatedAt;
}
