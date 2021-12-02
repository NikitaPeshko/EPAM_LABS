package com.epam.esc.dao;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.exception.ServiceException;
import com.epam.esc.model.Gift;

import java.util.List;

public interface GiftDAO {
    List<GiftDTO>  getAllGifts() throws NoEntityException;
    List<GiftDTO>  getAllGiftsSortByName(String sortingMethod);
    List<GiftDTO>  getAllGiftsSortByDate(String sortingMethod) throws NoEntityException;

    GiftDTO getGiftById(int id) throws DaoException;
    Gift addGift(Gift gift);
    boolean deleteGift(int id) throws ServiceException;
    Gift updateGift(int id,Gift gift);


}
