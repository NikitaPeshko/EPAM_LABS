package com.epam.esc.module3.controllers;

import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.Response;
import com.epam.esc.module3.service.userService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) throws NoEntityException {

        return userService.getUserById(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user) throws DAOException {
        return userService.addUser(user);

    }

    @DeleteMapping("/{id}")
    public boolean deleteUserById(@PathVariable("id") int id) {

        return userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user,@PathVariable("id") int id) {
        userService.updateUser(user,id);
    }

}
