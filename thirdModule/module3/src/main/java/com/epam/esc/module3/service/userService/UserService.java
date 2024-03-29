package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;

import java.util.List;


public interface UserService {
    User addUser(User user) throws DAOException;
    boolean deleteUserById(int id);
    User getUserById(int id) throws NoEntityException;
    List<User> getAllUsers(int numberOfPage,int numberOfItemOnPage);
    User updateUser(User user,int id);
    void buyGift(int userID,int giftID);
    List<Order> getAllUsersOrders(int id);
    Order getUserOrderById(int userID,int orderId) throws NoEntityException;
    List<Gift> buyGifts(int userID, List<Integer>gifts) throws NoEntityException;




}
