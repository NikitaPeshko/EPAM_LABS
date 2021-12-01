package com.epam.esc.DTO;

import java.util.Objects;
import java.util.Set;

public class GiftDTOWithTag {
    private int id;
    private String giftName;
    private Set<String> tags;

    public GiftDTOWithTag() {

    }

    public GiftDTOWithTag(int id, String giftName, Set<String> tags) {
        this.id = id;
        this.giftName = giftName;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftDTOWithTag that = (GiftDTOWithTag) o;
        return id == that.id && Objects.equals(giftName, that.giftName) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, giftName, tags);
    }
}
