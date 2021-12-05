package com.epam.esc.servise;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.dao.GiftDAOImp;
import com.epam.esc.entity.Gift;
import com.epam.esc.entity.TempGift;
import com.epam.esc.exception.DaoException;
import com.epam.esc.exception.NoEntityException;
import com.epam.esc.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImp implements GiftService{

    private GiftDAOImp giftDAOImp;

    @Autowired
    public GiftServiceImp(GiftDAOImp giftDAOImp) {
        this.giftDAOImp = giftDAOImp;
    }


    @Override
    public List<GiftDTO> getAllGifts() throws NoEntityException {
        return giftDAOImp.getAllGifts();
    }

    @Override
    public List<GiftDTO> getAllGiftsByPart(String partOfName, String discription) throws NoEntityException, ServiceException {
        return giftDAOImp.getAllGiftsByPart(partOfName,discription);
    }

    @Override
    public List<GiftDTO> getAllGiftsSortByName(String sortingMethod) {
        return giftDAOImp.getAllGiftsSortByName(sortingMethod);
    }

    @Override
    public List<GiftDTO> getGiftsByTag(String tag) {
        return giftDAOImp.getGiftsByTag(tag);
    }

    @Override
    public List<GiftDTO> getAllGiftsSortByDate(String sortingMethod) throws NoEntityException {
        return giftDAOImp.getAllGiftsSortByDate(sortingMethod);
    }

    @Override
    public GiftDTO getGiftById(int id) throws DaoException {
        return giftDAOImp.getGiftById(id);
    }

    @Override
    public Gift addGift(Gift gift) {
        return giftDAOImp.addGift(gift);
    }

    @Override
    public TempGift addGifttemp(TempGift gift) throws NoEntityException {
        return giftDAOImp.addGifttemp(gift);
    }

    @Override
    public boolean deleteGift(int id) throws ServiceException {
        return giftDAOImp.deleteGift(id);
    }

    @Override
    public Gift updateGift(int id, Gift gift) {
        return giftDAOImp.updateGift(id,gift);
    }
}
