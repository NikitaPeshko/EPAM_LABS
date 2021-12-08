package com.epam.esc.module3.dao;


import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class GiftDAOImpl implements GiftDAO{

    @Autowired
    private EntityManager entityManager;

    public List<Gift> getAllGift() {
        Session currentSession = entityManager.unwrap(Session.class);
        List<Gift> gifts =  currentSession.createQuery("From Gift").list();
        return gifts;
    }

    public Gift getGiftById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Gift gift = currentSession.get(Gift.class, id);
        return gift;
    }

    @Override
    public Gift addGift(Gift gift) throws DAOException {
        Session currentSession = entityManager.unwrap(Session.class);
        //currentSession.saveOrUpdate(gift);
        currentSession.save(gift);
        return gift;
    }

    @Override
    public boolean deleteGift(int id) {
        boolean result=false;
        Session session = entityManager.unwrap(Session.class);
        Gift gift=session.get(Gift.class, id);
        if(gift!=null){
            session.delete(gift);
            System.out.println("Gift 1 is deleted");
            result=true;
        }

        return result;
    }
}
