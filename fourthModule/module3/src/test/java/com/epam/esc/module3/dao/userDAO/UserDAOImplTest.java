package com.epam.esc.module3.dao.userDAO;

import com.epam.esc.module3.dao.giftDAO.GiftDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
@Transactional
class UserDAOImplTest {

    @Autowired
    private UserDAOImpl repository;
    @Autowired
    private GiftDAOImpl giftRepository;

    private User user;
    private Gift gift1;
    private Gift gift2;
    private Order order;



    @BeforeEach
    void setUp() {
        user=new User();
        user.setEmail("tempmail@mail.ru");
        user.setName("Ira");

        gift1=new Gift();
        gift1.setName("Fanta 0.25");
        gift1.setPrice(2);

        gift2=new Gift();
        gift2.setName("Bid mac ");
        gift2.setPrice(5);

        order=new Order();
        List<Gift> gifts=new LinkedList<>();
        gifts.add(gift1);
        gifts.add(gift2);
        order.setGiftsinorder(gifts);


    }


    @Test
    void getUserById() throws NoEntityException, DAOException {
        User user =new User();
        user.setEmail("sdnsdn@amil.ru");
        user.setName("Nikita");
        User user2 =new User();
        user2.setEmail("user2@mail.ru");
        user2.setName("Nikita2");
        repository.addUser(user);
        repository.addUser(user2);
        User userFromDb=repository.getUserById(2);
        assertThat(userFromDb.getEmail()).isEqualTo("user2@mail.ru");
    }

    @Test
    void addUser() throws DAOException, NoEntityException {
        repository.addUser(user);
        User userFromDB=repository.getUserById(1);
        assertThat(userFromDB.getName()).isEqualTo("Ira");
    }

    @Test
    void deleteUser() throws DAOException, NoEntityException {
        User user22 =new User();
        user22.setEmail("nikita@gmail.ru");
        user22.setName("Nikita");
        repository.addUser(user22);
        User userFromDb=repository.getUserById(1);
        assertThat(userFromDb.getEmail()).isEqualTo("nikita@gmail.ru");
        boolean result=repository.deleteUser(1);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void updateUser() throws DAOException {
        System.out.println(user.getName());
        repository.addUser(user);
        user.setName("Masha");
        System.out.println(user.getName());
        User newUserFromDB=repository.updateUser(user,1);
        assertThat(newUserFromDB.getName()).isEqualTo("Masha");
    }

    @Test
    void buyGift() throws DAOException, NoEntityException {
        giftRepository.addGift(gift1);
        giftRepository.addGift(gift2);
        List<Integer> giftsIds=new LinkedList<>();
        giftsIds.add(1);
        giftsIds.add(2);
        repository.addUser(user);
        List<Gift> buyGifts=new LinkedList<>();
        buyGifts=repository.buyGifts(1,giftsIds);
        System.out.println(buyGifts);
        assertThat(buyGifts.size()).isGreaterThan(0);
    }

    @Test
    void getAllOrders() throws DAOException, NoEntityException {
        repository.addUser(user);
        giftRepository.addGift(gift1);
        giftRepository.addGift(gift2);
        List<Integer> giftsIds=new LinkedList<>();
        giftsIds.add(1);
        giftsIds.add(2);
        List<Gift> buyGifts=new LinkedList<>();
        buyGifts=repository.buyGifts(1,giftsIds);
        List<Order>orders=new LinkedList<>();
        orders.add(order);
        assertThat(repository.getAllOrders(1).size()).isEqualTo(orders.size());
    }
}