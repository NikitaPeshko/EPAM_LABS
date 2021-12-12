package com.epam.esc.module3.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column
    private Timestamp dataOfOrder;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(int orderId, Timestamp dataOfOrder, User user) {
        this.orderId = orderId;
        this.dataOfOrder = dataOfOrder;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getDataOfOrder() {
        return dataOfOrder;
    }

    public void setDataOfOrder(Timestamp dataOfOrder) {
        this.dataOfOrder = dataOfOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && Objects.equals(dataOfOrder, order.dataOfOrder) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dataOfOrder, user);
    }
}
