package com.epam.esc.module3.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column
    private Timestamp dataOfOrder;
    private int quantity;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userInOrder;

    public Order() {
    }

    public Order(int orderId, Timestamp dataOfOrder, int quantity, User userInOrder) {
        this.orderId = orderId;
        this.dataOfOrder = dataOfOrder;
        this.quantity = quantity;
        this.userInOrder = userInOrder;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getUserInOrder() {
        return userInOrder;
    }

    public void setUserInOrder(User userInOrder) {
        this.userInOrder = userInOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && quantity == order.quantity && Objects.equals(dataOfOrder, order.dataOfOrder) && Objects.equals(userInOrder, order.userInOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dataOfOrder, quantity, userInOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", dataOfOrder=" + dataOfOrder +
                ", quantity=" + quantity +
                ", userInOrder=" + userInOrder +
                '}';
    }
}
