package com.epam.esc.dao;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.model.Gift;

import java.util.List;

public interface GiftDAO {
    List<GiftDTO>  getAllGifts();
    List<GiftDTO>  getAllGiftsSortByName(String sortingMethod);
    List<GiftDTO>  getAllGiftsSortByDate(String sortingMethod);

    GiftDTO getGiftById(int id);
    Gift addGift(Gift gift);
    boolean deleteGift(int id);
    Gift updateGift(int id,Gift gift);


}
