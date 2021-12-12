package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.dao.userDAO.UserDAOImpl;
import com.epam.esc.module3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAOImpl userDAO;

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
