package com.epam.esc.module3.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "gift_certigicate")
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gift_id;
    @Column
    private String name;
    @Column
    private String discription;
    @Column
    private int price;
    @Column
    private int duration;
    @Column
    private Timestamp createDate;
    @Column
    private Timestamp lastUpdateDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "gift_tag",
                joinColumns = @JoinColumn(name = "idgift"),
                inverseJoinColumns =@JoinColumn(name = "idtag") )
    private Set<Tag> listOfTag;

    public Gift() {
    }

    public Set<Tag> getListOfTag() {
        return listOfTag;
    }

    public void setListOfTag(Set<Tag> listOfTag) {
        this.listOfTag = listOfTag;
    }

    public int getId() {
        return gift_id;
    }

    public void setId(int id) {
        this.gift_id = id;
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
                "id='" + gift_id + '\'' +
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
        return price == gift.price && duration == gift.duration && Objects.equals(gift_id, gift.gift_id) && Objects.equals(name, gift.name) && Objects.equals(discription, gift.discription) && Objects.equals(createDate, gift.createDate) && Objects.equals(lastUpdateDate, gift.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gift_id, name, discription, price, duration, createDate, lastUpdateDate);
    }

    public static class Builder{
        private Gift newGift;

        public Builder() {
            newGift=new Gift();
        }
        public Builder setGiftId(int giftId) {
            newGift.gift_id = giftId;
            return this;
        }
        public Builder setGiftName(String name) {
            newGift.name = name;
            return this;
        }
        public Builder setGiftDiscription(String discription) {
            newGift.discription = discription;
            return this;
        }
        public Builder setGiftDuration(int duration) {
            newGift.duration = duration;
            return this;
        }
        public Builder setGiftPrice(int price) {
            newGift.price = price;
            return this;
        }
        public Builder setGiftCreateDate(Timestamp createDate) {
            newGift.createDate= createDate;
            return this;
        }
        public Builder setGiftLastUpdateDate(Timestamp lastUpdateDate) {
            newGift.lastUpdateDate= lastUpdateDate;
            return this;
        }
        public Builder setGiftTags(Set<Tag> tags) {
            newGift.listOfTag= tags;
            return this;
        }
        public Gift build() {
            return newGift;
        }


    }
}
