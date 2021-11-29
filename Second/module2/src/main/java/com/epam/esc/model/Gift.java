package com.epam.esc.model;


import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class Gift {
    private int id;
    private String name;
    private String discription;
    private int price;
    private int duration;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;
    private Set<Tag> listOfTag;


    public Gift(int id, String name, String discription, int price, int duration, Timestamp createDate, Timestamp lastUpdateDate, Set<Tag> listOfTag) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.listOfTag = listOfTag;
    }

    public Gift() {
    }

    public Set<Tag> getListOfTag() {
        return listOfTag;
    }

    public void setListOfTag(Set<Tag> listOfTag) {
        this.listOfTag = listOfTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return price == gift.price && duration == gift.duration && Objects.equals(id, gift.id) && Objects.equals(name, gift.name) && Objects.equals(discription, gift.discription) && Objects.equals(createDate, gift.createDate) && Objects.equals(lastUpdateDate, gift.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, discription, price, duration, createDate, lastUpdateDate);
    }
}
