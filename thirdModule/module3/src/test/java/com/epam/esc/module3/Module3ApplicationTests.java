package com.epam.esc.module3;

import com.epam.esc.module3.dao.userDAO.UserDAOImpl;
import com.epam.esc.module3.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
class Module3ApplicationTests {

	@Autowired
	private UserDAOImpl repository;


	@BeforeEach
	public void setUp() {

		User user =new User();
		user.setUserId(1);
		user.setEmail("sdnsdn@amil.ru");

	}


	@Test
	void testExample() throws Exception {
		User user =new User();
		user.setUserId(1);
		user.setEmail("sdnsdn@amil.ru");
		user.setName("Nikita");
		User genereateuser=repository.addUser(user);
		User userFromDb=repository.getUserById(1);
		assertThat(user.getEmail()).isEqualTo("sdnsdn@amil.ru");
		assertThat(user.getUserId()).isEqualTo(1);
	}

}
