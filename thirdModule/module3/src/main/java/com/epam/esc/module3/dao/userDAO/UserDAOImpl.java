package com.epam.esc.module3.dao.userDAO;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public User getUserById(int id) throws NoEntityException {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class,id);
        if(user==null){
            throw new NoEntityException("no user by this id","404not");
        }


        return user;
    }

    @Override
    public User addUser(User user) throws DAOException {
        Session currentSession = entityManager.unwrap(Session.class);
        if(user.getName()==null||user.getEmail()==null){
            throw new DAOException("Not enought parapeters","NEP");
        }
        currentSession.save(user);

        return user;

    }

    @Override
    @Transactional
    public boolean deleteUser(int id) {
        boolean result=false;
        Session session = entityManager.unwrap(Session.class);
        User user=session.get(User.class, id);
        if(user!=null){
            session.delete(user);
            result=true;
        }
        return result;
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.update(user);
    }


}
