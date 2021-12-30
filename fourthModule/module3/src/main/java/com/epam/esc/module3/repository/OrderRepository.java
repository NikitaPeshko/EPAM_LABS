package com.epam.esc.module3.repository;

import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findOrderByUserInOrder(User user);
    Order findOrderByUserInOrderAndOrderId(User user,int orderId);
}
