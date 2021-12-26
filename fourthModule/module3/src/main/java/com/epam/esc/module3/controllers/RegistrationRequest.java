package com.epam.esc.module3.controllers;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String login;
    private String password;
    private String email;
    private String name;
}
