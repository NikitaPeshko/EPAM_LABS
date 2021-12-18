package com.epam.esc.module3.service.userService;

import com.epam.esc.module3.dao.userDAO.UserDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserDAOImpl userDAOMock;

    private UserServiceImpl service;

    private User user;
    private User user2;
    private  List<Order> orders=new LinkedList<>();
    private Order order1;
    private Order order2;
    private Gift gift;
    private Gift gift2;
    

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service= new UserServiceImpl(userDAOMock);
        user=new User();
        user.setName("Nikita");
        user.setUserId(1);
        user.setEmail("tempemail@mail.ru");

        user2=new User();
        user2.setName("Misha");
        user2.setUserId(2);
        user2.setEmail("tempemail2@mail.ru");

        order1=new Order();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        order1.setOrderId(1);
        order1.setAmount(100);
        order1.setDataOfOrder(timestamp);

        order2=new Order();
        Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
        order2.setOrderId(2);
        order2.setAmount(1000);
        order2.setDataOfOrder(timestamp2);

        orders=new LinkedList<>();
        orders.add(order1);
        orders.add(order2);

        gift=new Gift();
        gift.setId(1);
        gift.setPrice(1000);
        gift.setName("Audi RS7");
        gift.setDuration(7);

        gift2=new Gift();
        gift2.setId(2);
        gift2.setPrice(10000);
        gift2.setName("Bentley Continental GT");
        gift2.setDuration(10);

        user.setOrders(orders);
    }

    @Test
    void buyGifts() throws NoEntityException {

        List<Integer>idOfGifts=new LinkedList<>(Arrays.asList(1,2));
        List<Gift> expected=new LinkedList<>();
        expected.add(gift);
        expected.add(gift2);
        when(userDAOMock.buyGifts(1,idOfGifts)).thenReturn(expected);
        List<Gift> actual=service.buyGifts(1,idOfGifts);
        verify(userDAOMock, times(1)).buyGifts(1,idOfGifts);
        assertThat(actual, is(expected));
    }

    @Test
    void addUser() throws DAOException {
        when(userDAOMock.addUser(user)).thenReturn(user);
        User actual=service.addUser(user);
        verify(userDAOMock, times(1)).addUser(user);
        assertThat(actual, is(user));
    }


    @Test
    void getUserById() throws NoEntityException {
        User expectedUser=new User();
        expectedUser.setUserId(1);
        expectedUser.setEmail("n.pesh@mail.ru");
        expectedUser.setName("Nikita");
        when(userDAOMock.getUserById(1)).thenReturn(expectedUser);
        User actual = service.getUserById(1);
        verify(userDAOMock, times(1)).getUserById(1);
        assertThat(actual, is(expectedUser));
    }

    @Test
    void getAllUsers() {
        List<User> users=new LinkedList<>();
        users.add(user);
        users.add(user2);
        when(userDAOMock.getAllUsers(0,5)).thenReturn(users);
        List<User> actual=service.getAllUsers(1,5);
        verify(userDAOMock, times(1)).getAllUsers(0,5);
        assertThat(actual, is(users));
    }

    @Test
    void updateUser() {
        User newUser=new User();
        newUser.setUserId(1);
        newUser.setEmail("temp@mailcom");
        newUser.setName("Masha");
        when(userDAOMock.updateUser(newUser,1)).thenReturn(newUser);
        User actual=service.updateUser(newUser,1);
        verify(userDAOMock, times(1)).updateUser(newUser,1);
        assertThat(actual, is(newUser));
    }

    @Test
    void getAllUsersOrders() {
        when(userDAOMock.getAllOrders(1)).thenReturn(orders);
        List<Order> actual=service.getAllUsersOrders(1);
        verify(userDAOMock, times(1)).getAllOrders(1);
        System.out.println(actual.size());
        assertThat(actual, is(orders));
    }

    @Test
    void getUserOrderById() throws NoEntityException {
        when(userDAOMock.getUserOrderById(1,2)).thenReturn(orders.get(1));
        Order actual=service.getUserOrderById(1,2);
        verify(userDAOMock, times(1)).getUserOrderById(1,2);
        assertThat(actual, is(orders.get(1)));
    }
}