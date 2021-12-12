package com.epam.esc.module3.dao.userDAO;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> users =  session.createQuery("From User ").list();
        return users;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.empty();
    }

    @Override
    public void addUser(User user) throws DAOException {

    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public void updateUser(User user) {

    }
}
