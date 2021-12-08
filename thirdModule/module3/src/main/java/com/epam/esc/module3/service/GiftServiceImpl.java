package com.epam.esc.module3.service;


import com.epam.esc.module3.dao.GiftDAOImpl;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftDAOImpl giftDAO;

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
}
