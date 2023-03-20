package me.upskill.schoolattendance.service;

import me.upskill.schoolattendance.controller.JwtTokenDTO;
import me.upskill.schoolattendance.controller.LoginDTO;
import me.upskill.schoolattendance.controller.RegisterDTO;
import me.upskill.schoolattendance.controller.UserDTO;

public interface AuthService {

    // register a user and return the registered user
    UserDTO register(RegisterDTO dto);

    // login a user and return the jwt token
    JwtTokenDTO login(LoginDTO dto);
}
