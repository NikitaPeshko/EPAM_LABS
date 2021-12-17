package com.epam.esc.module3.dao.userDAO;

import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
@Transactional
class UserDAOImplTest {

    @Autowired
    private UserDAOImpl repository;

    @Test
    void getAllUsers() {
    }

    @Test
    void getUserById() throws NoEntityException, DAOException {
        User user =new User();
      //
        user.setEmail("sdnsdn@amil.ru");
        user.setName("Nikita");

        User user2 =new User();
        //
        user2.setEmail("user2@mail.ru");
        user2.setName("Nikita2");
        repository.addUser(user);
        repository.addUser(user2);
        User userFromDb=repository.getUserById(2);
        assertThat(userFromDb.getEmail()).isEqualTo("user2@mail.ru");
    }

    @Test
    void addUser() {
    }

    @Test
    void deleteUser() throws DAOException, NoEntityException {
        User user =new User();
        user.setEmail("nikita@gmail.ru");
        user.setName("Nikita");
        repository.addUser(user);
        User userFromDb=repository.getUserById(1);
        assertThat(userFromDb.getEmail()).isEqualTo("nikita@gmail.ru");
        boolean result=repository.deleteUser(1);
        assertThat(result).isEqualTo(true);
    }

    @Test
    void updateUser() {
    }

    @Test
    void buyGift() {
    }

    @Test
    void getAllOrders() {
    }
}