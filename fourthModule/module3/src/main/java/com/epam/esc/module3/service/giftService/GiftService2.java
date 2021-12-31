package com.epam.esc.module3.service.giftService;

import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Order;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.entity.User;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.ServiceException;
import com.epam.esc.module3.repository.GiftRepository;
import com.epam.esc.module3.repository.OrderRepository;
import com.epam.esc.module3.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GiftService2 {

    private GiftRepository giftRepository;
    private OrderRepository orderRepository;
    private TagRepository tagRepository;


    @Autowired
    public GiftService2(GiftRepository giftRepository, OrderRepository orderRepository, TagRepository tagRepository) {
        this.giftRepository = giftRepository;
        this.orderRepository = orderRepository;
        this.tagRepository = tagRepository;
    }






    public List<Gift> getAllGift(int numberOfPage,int numberOfItemOnPage,String sortingBy,String sortingMethod){
        Pageable page;
        if(sortingBy.equalsIgnoreCase("name")){
            if(sortingMethod.equalsIgnoreCase("asc")){
                page = PageRequest.of(numberOfPage-1,numberOfItemOnPage, Sort.by(sortingBy).ascending());
                return giftRepository.findAll(page).getContent();
            }else{
                page = PageRequest.of(numberOfPage-1,numberOfItemOnPage, Sort.by(sortingBy).descending());
                return giftRepository.findAll(page).getContent();
            }
        }
        page=PageRequest.of(numberOfPage-1,numberOfItemOnPage);

        return giftRepository.findAll(page).getContent();
    }

    public Optional<Gift> getGiftById(int id) {
        return giftRepository.findById(id);
    }

    public Gift saveGift(Gift gift) {
        return giftRepository.save(gift);
    }

    public boolean deleteGift(int id) throws ServiceException {
        if(giftRepository.existsById(id)){
            giftRepository.deleteById(id);
        }else {
            throw new ServiceException("Gift by this id not exist","ERRORCODE");
        }
        return !giftRepository.existsById(id);
    }

    public List<Gift> findByPartName(String partOfName,Pageable pageable) throws NoEntityException {
        List<Gift> list=giftRepository.findByNameContaining(partOfName,pageable).getContent();

        if(list.isEmpty()){
            throw new NoEntityException("No gift by this name:"+partOfName,"ERRORCODE");
        }
        return list;
    }


    public List<Gift> findGiftBySeveralTags(List<String> tags) throws NoEntityException {

        String str="";
        for (String tag:tags){
            str=str+"%"+tag+"%,";
        }
        String tagsInStringFormat=str.substring(0,str.length()-1);
        String havingInSQLQuary=tagsInStringFormat;

        List<Gift> gifts=giftRepository.findGiftBySeveralTags(tagsInStringFormat);
        if (gifts.isEmpty()){
            throw new NoEntityException("No gifts with this tags","ERRORCODE");

        }

        return gifts;
    }
    public Gift changePriceOfgift(int id,int newprice) {
        Gift giftFromDB=giftRepository.getOne(id);
        giftFromDB.setPrice(newprice);
        return giftRepository.save(giftFromDB);
    }


    public Tag findMostPopularTag() throws NoEntityException {
        int userId=giftRepository.findUserIdWithMaxAmountOfAllOrders();
        User user=new User();
        user.setUserId(userId);

        List<Order> orders=orderRepository.findOrderByUserInOrder(user);
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
        if (tagsAndNubmers.isEmpty()){
            throw new NoEntityException("No anyone tag in this order","ERROR");

        }
        int maxValue=Collections.max(tagsAndNubmers.values());
        for (Map.Entry<String, Integer> entry : tagsAndNubmers.entrySet()){
            if(entry.getValue()==maxValue){
                nameOfmostPopularTag=entry.getKey();
            }
        }
        if (nameOfmostPopularTag==null){
            throw new NoEntityException("No anyone tag in this order","ERROR");
        }
        System.out.println(nameOfmostPopularTag);
        Tag mostPopularTag=tagRepository.findByTagName(nameOfmostPopularTag);
        if (mostPopularTag==null){
            throw new NoEntityException("No anyone tag in this order","ERROR");
        }
        return mostPopularTag;
    }




}
