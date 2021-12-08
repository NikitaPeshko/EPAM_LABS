package com.epam.esc.module3.service;

import com.epam.esc.module3.entity.Gift;

import java.util.List;

public interface GiftService {
    List<Gift> getAllGift();
    Gift getGiftById(int id);
}
