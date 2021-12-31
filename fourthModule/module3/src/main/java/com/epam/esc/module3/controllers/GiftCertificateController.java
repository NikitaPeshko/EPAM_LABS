package com.epam.esc.module3.controllers;


import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.exception.ServiceException;
import com.epam.esc.module3.service.giftService.GiftService2;
import com.epam.esc.module3.service.giftService.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gifts")
public class GiftCertificateController {

    @Autowired
    private GiftServiceImpl giftService;

    @Autowired
    private GiftService2 giftService2;

    @GetMapping()
    public List<Gift> getAll(@RequestParam(name = "page",defaultValue = "1") int numberOfPage,
                             @RequestParam(name = "content",defaultValue = "5") int numberOfItemOnPage,
                             @RequestParam(name="sortby",defaultValue = "default") String sortingBy,
                             @RequestParam(name="sort",defaultValue = "asc") String sortingMethod){

        return giftService2.getAllGift(numberOfPage,numberOfItemOnPage,sortingBy,sortingMethod);
    }


    @GetMapping("/{id}")
    public Optional<Gift> getGiftById(@PathVariable int id) throws NoEntityException {
        Optional<Gift> gift = giftService2.getGiftById(id);
        if(gift.isEmpty()) {
            throw new NoEntityException("Gift not found for the Id:"+id,"ERRORCODE");
        }
        System.out.println(gift);
        return gift;
    }

    @PostMapping
    public Gift addGift(@RequestBody Gift gift) throws DAOException {
        giftService2.saveGift(gift);
        return gift;
    }

    @DeleteMapping("/{id}")
    public boolean deleteGift(@PathVariable int id) throws ServiceException {
        return giftService2.deleteGift(id);
    }

    @GetMapping(value = "/findbyname")
    public List<Gift> getGiftByName(@RequestParam(name="name",defaultValue = "")String name,
                                    @RequestParam(name = "page",defaultValue = "1") int numberOfPage,
                                    @RequestParam(name = "content",defaultValue = "5") int numberOfItemOnPage
                                    ) throws NoEntityException, ServiceException {
        Pageable page= PageRequest.of(numberOfPage-1,numberOfItemOnPage);
        return giftService2.findByPartName(name,page);
    }


    @PutMapping("/changeprice/{id}")
    public Gift changePriceOfGift(@PathVariable("id") int id,@RequestParam("price")int newprice){
        return giftService2.changePriceOfgift(id,newprice);
    }

    @GetMapping("/findbyseveraltags")
    public List<Gift> findGiftsBySeveralTags(@RequestParam("tag")List<String>tags) throws NoEntityException {
        System.out.println(tags);
        return giftService2.findGiftBySeveralTags(tags);

    }

    @GetMapping("/mostpopulartag")
    public Tag findGiftsBySeveralTags() throws NoEntityException {
        return giftService2.findMostPopularTag();
    }


}
