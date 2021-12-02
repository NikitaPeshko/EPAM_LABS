package com.epam.esc.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class TempGift {
    private int id;
    private String tags;
    private String name;
    private String discription;
    private int price;
    private int duration;
    private Timestamp createDate;
    private Timestamp lastUpdateDate;


    public TempGift(int id, String tags, String name, String discription, int price, int duration, Timestamp createDate, Timestamp lastUpdateDate) {
        this.id = id;
        this.tags = tags;
        this.name = name;
        this.discription = discription;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public TempGift() {
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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
        return "TempGift{" +
                "id=" + id +
                ", tags='" + tags + '\'' +
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
        TempGift tempGift = (TempGift) o;
        return id == tempGift.id && price == tempGift.price && duration == tempGift.duration && Objects.equals(name, tempGift.name) && Objects.equals(discription, tempGift.discription) && Objects.equals(createDate, tempGift.createDate) && Objects.equals(lastUpdateDate, tempGift.lastUpdateDate) && Objects.equals(tags, tempGift.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, discription, price, duration, createDate, lastUpdateDate, tags);
    }
}
