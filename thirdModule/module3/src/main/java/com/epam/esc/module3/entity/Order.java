package com.epam.esc.module3.entity;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @Column
    private Timestamp dataOfOrder;
    @Column
    private int amount;
//////////////////
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Gift> giftsinorder;


/////////////////////////////
    @ManyToOne
    @JoinColumn(name = "userinorder_id")
    private User userInOrder;

    public Order() {
    }

    public Order(int orderId, Timestamp dataOfOrder, int amount, List<Gift> giftsinorder) {
        this.orderId = orderId;
        this.dataOfOrder = dataOfOrder;
        this.amount = amount;
        this.giftsinorder = giftsinorder;
    }

    public List<Gift> getGiftsinorder() {
        return giftsinorder;
    }

    public void setGiftsinorder(List<Gift> giftsinorder) {
        this.giftsinorder = giftsinorder;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId && amount == order.amount && Objects.equals(dataOfOrder, order.dataOfOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, dataOfOrder, amount);
    }
}
