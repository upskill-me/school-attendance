package me.upskill.schoolattendance.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jakarta.transaction.Transactional;
import me.upskill.schoolattendance.api.entities.jpa.Role;
import me.upskill.schoolattendance.api.entities.jpa.User;
import me.upskill.schoolattendance.controller.JwtTokenDTO;
import me.upskill.schoolattendance.controller.LoginDTO;
import me.upskill.schoolattendance.controller.RegisterDTO;
import me.upskill.schoolattendance.controller.UserDTO;
import me.upskill.schoolattendance.exceptions.MyException;
import me.upskill.schoolattendance.exceptions.StatusCodeMyException;
import me.upskill.schoolattendance.properties.AppProperties;
import me.upskill.schoolattendance.repository.RoleRepository;
import me.upskill.schoolattendance.repository.UserRepository;
import me.upskill.schoolattendance.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static me.upskill.schoolattendance.api.ErrorCodes.INTERNAL_SERVER_ERROR;

@Service
public class DefaultAuthService implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppProperties properties;

    @Override
    @Transactional
    public UserDTO register(RegisterDTO dto) {
        // email unique hai ya nahi hai
        // username unique hai ya nahi hai

        Optional<User> eUser = userRepository.findByEmail(dto.getEmail());
        if (eUser.isPresent()) {
            throw new StatusCodeMyException("E1-6100", "email must be unique, choose a different email", 400);
        }

        Optional<User> uUser = userRepository.findByUsername(dto.getUsername());
        if (uUser.isPresent()) {
            throw new StatusCodeMyException("E1-6110", "username must be unique, choose a different username", 400);
        }

        // ab sab sahi hai to password to hash me badalna padega
        String hashed = BCrypt.withDefaults().hashToString(12, dto.getPassword().toCharArray());

        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(hashed);
        entity.setUsername(dto.getUsername());

        User savedUser = userRepository.save(entity);

        return UserDTO.builder()
                .createdAt(savedUser.getCreatedAt())
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();
    }


    @Override
    @Transactional
    public JwtTokenDTO login(LoginDTO dto) {
        Optional<User> uUser = userRepository.findByUsername(dto.getUsername());

        if (uUser.isEmpty()) {
            Optional<User> eUser = userRepository.findByEmail(dto.getUsername());

            if (eUser.isEmpty()) {
                throw new StatusCodeMyException("E1-6200", "username not found", 404);
            }

            uUser = eUser;
        }

        // password match
        BCrypt.Result result = BCrypt.verifyer().verify(dto.getPassword().toCharArray(), uUser.get().getPassword());
        if (!result.verified) {
            // password is invalid
            throw new StatusCodeMyException("E1-3000", "password is invalid", 401);
        }

        // ab sab sahi hai, password bhi sahi hai, generate jwt
        try {
            Algorithm algorithm = Algorithm.HMAC256(properties.getSecretKey());

            Instant expire = Instant.now().plusSeconds(2 * 60 * 60);

            // read roles from table
            List<Role> roles = roleRepository.findAllByUserId(uUser.get().getId());
            List<String> sroles = roles.stream().map(Role::getName).collect(Collectors.toList());

            // {header}.{payload}.{signature}
            String token = JWT.create()
                    .withClaim("username", uUser.get().getUsername())
                    .withClaim("userId", uUser.get().getId())
                    .withClaim("roles", sroles)
                    .withIssuer(properties.getServiceName())
                    .withExpiresAt(expire)
                    .sign(algorithm);

            return JwtTokenDTO.builder().token(token).build();
        } catch (JWTCreationException exception) {
            throw new MyException(INTERNAL_SERVER_ERROR, "failed to login, please try again later");
        }
    }
}
