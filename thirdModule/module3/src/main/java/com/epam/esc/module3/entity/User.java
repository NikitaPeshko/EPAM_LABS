package com.epam.esc.module3.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String name;
    @Column
    private String email;

    @OneToMany(mappedBy = "userInOrder",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Gift> gifts;

    public User() {
    }

    public User(int userId, String name, String email, List<Order> orders, List<Gift> gifts) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.orders = orders;
        this.gifts = gifts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(orders, user.orders) && Objects.equals(gifts, user.gifts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, orders, gifts);
    }
}
