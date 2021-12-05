package com.epam.esc.servise;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.entity.Gift;
import com.epam.esc.entity.TempGift;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.exception.ServiceException;

import java.util.List;

public interface GiftService {
    List<GiftDTO> getAllGifts() throws NoEntityException;
    List<GiftDTO> getAllGiftsByPart(String partOfName,String discription) throws NoEntityException, ServiceException;
    List<GiftDTO> getAllGiftsSortByName(String sortingMethod);
    List<GiftDTO> getGiftsByTag(String tag);
    List<GiftDTO> getAllGiftsSortByDate(String sortingMethod) throws NoEntityException;
    GiftDTO getGiftById(int id) throws DaoException;
    Gift addGift(Gift gift);
    TempGift addGifttemp(TempGift gift) throws NoEntityException;
    boolean deleteGift(int id) throws ServiceException;
    Gift updateGift(int id, Gift gift);

}
