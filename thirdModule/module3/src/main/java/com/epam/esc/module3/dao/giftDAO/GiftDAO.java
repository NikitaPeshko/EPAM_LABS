package com.epam.esc.module3.dao.giftDAO;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.exception.DAOException;

import java.util.List;

public interface GiftDAO {
    List<Gift> getAllGift();
    Gift getGiftById(int id);
    Gift addGift(Gift gift) throws DAOException;
    boolean deleteGift(int id);
    List<Gift> findGiftByPatName(String partOfName);
    List<Gift> findByTagName(String tagName);

}
