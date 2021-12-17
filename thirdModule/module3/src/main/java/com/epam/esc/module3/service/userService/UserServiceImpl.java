package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.dao.userDAO.UserDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Override
    public void buyGift(int userID, int giftID) {
        userDAO.buyGift(userID,giftID);
    }

    @Override
    public void buyGifts(int userID, List<Integer>gifts) throws NoEntityException {
        userDAO.buyGifts(userID,gifts);
    }

    @Autowired
    private UserDAOImpl userDAO;

    @Override
    @Transactional
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
    public List<User> getAllUsers(int numberOfPage,int numberOfItemOnPage) {
        int firstitem=(numberOfPage-1)*numberOfItemOnPage;

        return userDAO.getAllUsers(firstitem,numberOfItemOnPage);
    }

    @Override
    public void updateUser(User user,int id) {
        user.setUserId(id);
        userDAO.updateUser(user,id);
    }


    @Override
    public List<Order> getAllUsersOrders(int id) {
        return userDAO.getAllOrders(id);
    }

    @Override
    public Order getUserOrderById(int userID,int orderId) throws NoEntityException {
        return userDAO.getUserOrderById(userID,orderId);
    }


}
