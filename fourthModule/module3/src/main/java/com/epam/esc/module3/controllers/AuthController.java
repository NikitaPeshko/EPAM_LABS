package com.epam.esc.module3.controllers;

import com.epam.esc.module3.config.jwt.JwtProvider;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.service.userService.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService1 userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegistrationRequest registrationRequest) throws NoEntityException {
        User u = new User();
        u.setPassword(registrationRequest.getPassword());
        u.setLogin(registrationRequest.getLogin());
        u.setEmail(registrationRequest.getEmail());
        u.setName(registrationRequest.getName());
        if(!userService.checkIfLoginNotExist(registrationRequest.getLogin())){
            userService.saveUser(u);
        }else {
            throw new NoEntityException("This login already exist","ERROR");
        }
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) throws NoEntityException {
        User userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}
