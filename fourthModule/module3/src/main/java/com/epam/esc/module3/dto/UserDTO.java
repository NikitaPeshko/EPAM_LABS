package com.epam.esc.module3.dto;

import com.epam.esc.module3.entity.User;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class UserDTO extends RepresentationModel<UserDTO> {
    private int id;
    private String name;
    private String email;
    private String login;
    private String roleName;
}
