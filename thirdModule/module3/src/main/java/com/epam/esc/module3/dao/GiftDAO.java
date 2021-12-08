package com.epam.esc.module3.dao;

import com.epam.esc.module3.entity.Gift;

import java.util.List;

public interface GiftDAO {
    List<Gift> getAllGift();
    Gift getGiftById(int id);
}
