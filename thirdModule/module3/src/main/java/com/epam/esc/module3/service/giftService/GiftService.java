package com.epam.esc.module3.service.giftService;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.exception.DAOException;

import java.util.List;

public interface GiftService {
    List<Gift> getAllGift();
    Gift getGiftById(int id);
    Gift addGift(Gift gift) throws DAOException;
    boolean deleteGift(int id);
}
