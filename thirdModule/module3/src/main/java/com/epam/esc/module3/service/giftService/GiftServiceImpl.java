package com.epam.esc.module3.service.giftService;


import com.epam.esc.module3.dao.giftDAO.GiftDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {


    private GiftDAOImpl giftDAO;

    @Autowired
    public GiftServiceImpl(GiftDAOImpl giftDAO) {
        this.giftDAO = giftDAO;
    }

    @Override
    public List<Gift> getAllGift() {
        return giftDAO.getAllGift();
    }

    @Override
    public Gift getGiftById(int id) {
        return giftDAO.getGiftById(id);
    }

    @Override
    public Gift addGift(Gift gift) throws DAOException {
        return giftDAO.addGift(gift);
    }

    @Override
    public boolean deleteGift(int id) {
        return giftDAO.deleteGift(id);

    }
    @Override
    public List<Gift> findByName(String partOfName) throws NoEntityException {
        return giftDAO.findGiftByPatName(partOfName);
    }

    @Override
    public List<Gift> findGiftByTagName(String tagName) throws NoEntityException {
        return giftDAO.findByTagName(tagName);
    }

    @Override
    public Gift changePriceOfGift(int id, int newPrice) {
        return giftDAO.changePriceOfGift(id,newPrice);
    }

    @Override
    public List<Gift> findGiftBySeveralTags(List<String> tags) throws NoEntityException {
        return giftDAO.findGiftBySeveralTags(tags);
    }

    @Override
    public Tag findMostPopulaarTag() throws NoEntityException {
        if (giftDAO.findMostPopularTag()==null){
            throw new NoEntityException("No tag","ERRORCODE");
        }
        return giftDAO.findMostPopularTag();
    }
}
