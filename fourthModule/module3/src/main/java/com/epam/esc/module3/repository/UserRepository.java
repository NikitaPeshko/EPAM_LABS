package com.epam.esc.module3.repository;

import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
    boolean existsByLogin(String login);
    Page<User> findAll(Pageable pageable);


}
