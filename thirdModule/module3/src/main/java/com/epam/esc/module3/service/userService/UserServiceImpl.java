package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.dao.userDAO.UserDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAOImpl userDAO;

    @Override
    public User addUser(User user) throws DAOException {
        return userDAO.addUser(user);

    }

    @Override
    public boolean deleteUserById(int id) {
        return userDAO.deleteUser(id);

    }

    @Override
    public User getUserById(int id) throws NoEntityException {

        return userDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(User user,int id) {
        user.setUserId(id);
        userDAO.updateUser(user);
    }


}
