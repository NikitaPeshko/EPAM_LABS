package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    void deleteUserById(int id);
    Optional<User> getUserById(int id);
    List<User> getAllUsers();


}
