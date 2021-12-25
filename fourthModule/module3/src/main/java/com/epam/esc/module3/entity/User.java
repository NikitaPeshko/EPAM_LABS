package com.epam.esc.module3.entity;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User extends RepresentationModel<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column
    private String login;
    @Column
    private String password;

    @Column
    private String name;
    @Column
    private String email;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Gift> gifts;



///////////////////////////////////////////////
    @OneToMany(mappedBy = "userInOrder",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Order> orders;

    public User() {
    }

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;



    public User(int userId, String login, String password, String name, String email, List<Gift> gifts, List<Order> orders, Role role) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.gifts = gifts;
        this.orders = orders;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
        return userId == user.userId && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(gifts, user.gifts) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email, gifts, orders);
    }
}
