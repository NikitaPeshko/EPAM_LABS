package com.epam.esc.module3.dao.giftDAO;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;

import java.util.List;

public interface GiftDAO {
    List<Gift> getAllGift();
    Gift getGiftById(int id);
    Gift addGift(Gift gift) throws DAOException;
    boolean deleteGift(int id);
    List<Gift> findGiftByPatName(String partOfName) throws NoEntityException;
    List<Gift> findByTagName(String tagName) throws NoEntityException;
    Gift changePriceOfGift(int id,int newPrice);
    List<Gift> findGiftBySeveralTags(List<String> tags) throws NoEntityException;
    Tag findMostPopularTag();

}
