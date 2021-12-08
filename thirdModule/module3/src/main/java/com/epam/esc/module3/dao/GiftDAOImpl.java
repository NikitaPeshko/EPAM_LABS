package com.epam.esc.module3.dao;


import com.epam.esc.module3.entity.Gift;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
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
}
