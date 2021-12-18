package com.epam.esc.module3.dao.userDAO;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers(int firstitem,int numberOfItemOnPage) {
        Session session = entityManager.unwrap(Session.class);
        Query query=session.createQuery("From User ");
        query.setFirstResult(firstitem);
        query.setMaxResults(numberOfItemOnPage);
        List<User> users =  query.list();
        return users;
    }

    @Override
    public User getUserById(int id) throws NoEntityException {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class,id);
        if(user==null){
            throw new NoEntityException("no user by this id","404not");
        }
        return user;
    }

    @Override
    public User addUser(User user) throws DAOException {
        Session currentSession = entityManager.unwrap(Session.class);
        if(user.getName()==null||user.getEmail()==null){
            throw new DAOException("Not enought parapeters","NEP");
        }
        currentSession.save(user);


        return user;

    }

    @Override
    @Transactional
    public boolean deleteUser(int id) {
        boolean result=false;
        Session session = entityManager.unwrap(Session.class);
        User user=session.get(User.class, id);
        if(user!=null){
            session.delete(user);
            result=true;
        }
        return result;
    }

    @Override
    @Transactional
    public User updateUser(User user,int id) {
        Session session = entityManager.unwrap(Session.class);
        User userfromdb=session.get(User.class,id);
        if(user.getName()==null){
            userfromdb.setEmail(user.getEmail());
        }
        if (user.getEmail()==null){
            userfromdb.setName(user.getName());
        }
        if ((user.getName()!=null)&&(user.getEmail()!=null)){
            userfromdb.setName(user.getName());
            userfromdb.setEmail(user.getEmail());
            session.update(userfromdb);

        }else
        {
            session.update(userfromdb);
        }
        return userfromdb;
    }


    @Override
    @Transactional
    public void buyGift(int userID, int giftID) {
        Session session = entityManager.unwrap(Session.class);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));
        User user=new User();
        user.setUserId(userID);
        Gift gift=session.get(Gift.class,giftID);
        gift.setUser(user);
        List<Gift> list=new LinkedList<Gift>();
        list.add(gift);
        Order order=new Order();
        order.setDataOfOrder(timestamp);
        order.setAmount(gift.getPrice());
        order.setGiftsinorder(list);
        order.setUserInOrder(user);
        session.save(order);
        System.out.println(order.getOrderId());
        gift.setOrder(order);
    }



    @Override
    @Transactional
    public List<Gift> buyGifts(int userID, List<Integer>giftsID) throws NoEntityException {
        Session session = entityManager.unwrap(Session.class);
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));




        User user=new User();
        user.setUserId(userID);
        List<Gift> list=new LinkedList<Gift>();

        for (Integer giftID:giftsID){
            Gift gift=session.get(Gift.class,giftID);
            Query query=session.createSQLQuery("select user_id from gift_certigicate where gift_id=:nameofgiftid");
            Integer result= (Integer) query.setParameter("nameofgiftid",gift).uniqueResult();
            if ((result!=null)&&(result>0)){
                throw new NoEntityException("This certificate id="+giftID+" already buy by another user","ERRORCODE");
            }

            gift.setUser(user);

            list.add(gift);
        }
        Order order=new Order();
        order.setDataOfOrder(timestamp);
        int amountOfAllOrder=0;
        for (Gift gift:list){
            amountOfAllOrder=amountOfAllOrder+gift.getPrice();
        }
        order.setAmount(amountOfAllOrder);
        order.setGiftsinorder(list);
        order.setUserInOrder(user);
        session.save(order);
        System.out.println(order.getOrderId());
        for (Gift gift:list){
            gift.setOrder(order);
        }
        return list;
    }


    @Override
    public List<Order> getAllOrders(int id) {
        Session session = entityManager.unwrap(Session.class);
        User user=new User();
        user.setUserId(id);
        Query query=session.createQuery("From Order where userInOrder=:userId ");
        query.setParameter("userId",user);
        List<Order> orders=query.list();
        return orders;
    }

    @Override
    public Order getUserOrderById(int userID,int orderId) throws NoEntityException {
        Session session = entityManager.unwrap(Session.class);
        User user=new User();
        user.setUserId(userID);
        Query query=session.createQuery("From Order where userInOrder=:userId and orderId=:orderID");
        query.setParameter("userId",user);
        query.setParameter("orderID",orderId);
        List<Order> order=query.list();
        if (order.isEmpty()){
            throw new NoEntityException("No order with this ID in this user","ERRORCODE");
        }
        return order.get(0);
    }
}
