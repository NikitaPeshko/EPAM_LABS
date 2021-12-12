package com.epam.esc.module3.dao.userDAO;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    void addUser(User user) throws DAOException;
    boolean deleteUser(int id);
    void updateUser(User user);

}
