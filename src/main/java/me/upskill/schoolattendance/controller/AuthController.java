package me.upskill.schoolattendance.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.upskill.schoolattendance.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController extends AbstractController {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(HttpServletRequest request, HttpServletResponse response, @RequestBody RegisterDTO body) {
        assertValidEmail(body.getEmail(), "Please enter a valid email");
        assertValid(body.getFirstName(), "Please enter a valid first name");
        assertValid(body.getLastName(), "Please enter a valid last name");
        assertValid(body.getUsername(), "Please enter a valid username");
        assertValid(body.getPassword(), "Please enter a valid password");


        UserDTO res = authService.register(body);

        return ResponseEntity.status(201).body(res);
    }


    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginDTO body) {
        assertValid(body.getUsername(), "Please enter a valid username");
        assertValid(body.getPassword(), "Please enter a valid password");

        JwtTokenDTO res = authService.login(body);

        return ResponseEntity.status(200).body(res);
    }
}
