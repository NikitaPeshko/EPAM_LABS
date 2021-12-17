package com.epam.esc.module3.dao.giftDAO;


import com.epam.esc.module3.dao.giftDAO.GiftDAO;
import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.DAOException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Stream;

@Repository
@Transactional
public class GiftDAOImpl implements GiftDAO {

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
    @Override
    public List<Gift> findGiftByPatName(String partOfName) {
        Session session = entityManager.unwrap(Session.class);
        Query query=session.createSQLQuery("select * from gift_certigicate where name like :partOfName").addEntity(Gift.class);
        query.setParameter("partOfName","%"+partOfName+"%");
        List<Gift> gifts =query.list();

        return gifts;
    }

    @Override
    public List<Gift> findByTagName(String tagName) {
        Session session = entityManager.unwrap(Session.class);
        List<String> tagsInList= List.of(tagName.split(","));
        System.out.println(tagsInList);
        Set<String> tags=new HashSet<>(tagsInList);
        System.out.println(tags);
        List<String>lists=new ArrayList<>();
        lists.add("audi");
        Set<Tag>tags1=new HashSet<Tag>();
        Tag taggg=new Tag(1,"mersedes");
        tags1.add(taggg);

        String sql="select gift_certigicate.*, group_concat(t1.tag_name)as tags \n" +
                "from gift_certigicate join gift_tag ft1 \n" +
                "on ft1.idgift = gift_certigicate.gift_id join tags t1 on t1.tag_id = ft1.idtag \n" +
                "join gift_tag ft2\n" +
                "on ft2.idgift = gift_certigicate.gift_id\n" +
                "join tags t2 on t2.tag_id = ft2.idtag and t2.tag_name =:nameoftag \n" +
                "group by gift_certigicate.gift_id";


        Query query=session.createSQLQuery(sql).addEntity(Gift.class);
        query.setString("nameoftag",tagName);
    //    query.setParameter("nameoftag","'"+tagName+"'");

        List<Gift> gifts=query.list();



        return gifts;
    }


    @Override
    @Transactional
    public Gift changePriceOfGift(int id, int newPrice) {
        Session session = entityManager.unwrap(Session.class);
        Gift gift=session.get(Gift.class,id);
        gift.setPrice(newPrice);
        session.update(gift);
        Gift giftWithNewPrice=session.get(Gift.class,id);


        return giftWithNewPrice;
    }

    @Override
    public List<Gift> findGiftBySeveralTags(List<String> tags) {
        Session session = entityManager.unwrap(Session.class);
        String sql="select gift_certigicate.*, group_concat(t1.tag_name)as tags \n" +
                "from gift_certigicate join gift_tag ft1 \n" +
                "on ft1.idgift = gift_certigicate.gift_id \n" +
                "join tags t1 on t1.tag_id = ft1.idtag \n" +
                "group by gift_certigicate.gift_id";
        String str="";
        for (String tag:tags){
            str=str+"%"+tag+"%,";
        }
        String tagsInStringFormat=str.substring(0,str.length()-1);
        String havingInSQLQuary=" having tags like '"+tagsInStringFormat+"'";
        sql=sql+havingInSQLQuary;
        System.out.println(sql);


        Query query=session.createSQLQuery(sql).addEntity(Gift.class);
        List<Gift> gifts=query.list();

        return gifts;
    }

    @Override
    public Tag findMostPopularTag() {
        Session session = entityManager.unwrap(Session.class);
        final String sql="select userinorder_id from(select sum(amount) summa,userinorder_id from dbmodule3.orders group by userinorder_id order by summa desc limit 1) k";
        int userId=(int)session.createSQLQuery(sql).uniqueResult();
        User user=new User();
        user.setUserId(userId);
        Query query=session.createQuery("From Order where userInOrder=:userId");
        query.setParameter("userId",user);
        List<Order> orders=query.list();
        List<Gift> gifts=new LinkedList<>();
        for (Order order:orders){
            for (Gift gift:order.getGiftsinorder()){
                gifts.add(gift);
            }
        }
        List<Tag> allTags=new LinkedList<>();
        for (Gift gift:gifts){
            for (Tag tag:gift.getListOfTag()){
                allTags.add(tag);
            }
        }
        Map<String,Integer> tagsAndNubmers=new HashMap<>();
        for (Tag tag:allTags){
            if(tagsAndNubmers.containsKey(tag.getTagName())){
                tagsAndNubmers.put(tag.getTagName(),tagsAndNubmers.get(tag.getTagName())+1);
            }else{
                tagsAndNubmers.put(tag.getTagName(),1);

            }
        }
        String nameOfmostPopularTag="";
        int maxValue=Collections.max(tagsAndNubmers.values());

        for (Map.Entry<String, Integer> entry : tagsAndNubmers.entrySet()){
            if(entry.getValue()==maxValue){
                nameOfmostPopularTag=entry.getKey();
            }

        }
        Query query1=session.createQuery("From Tag where tagName=:nameofmostpopulartag");
        query1.setParameter("nameofmostpopulartag",nameOfmostPopularTag);
        List<Tag> mostPopulatTag=  query1.list();
        return mostPopulatTag.get(0);
    }
}
