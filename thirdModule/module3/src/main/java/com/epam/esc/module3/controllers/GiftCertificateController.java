package com.epam.esc.module3.controllers;


import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.exception.NoEntityException;
import com.epam.esc.module3.service.giftService.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftCertificateController {

    @Autowired
    private GiftServiceImpl giftService;

    @GetMapping()
    public List<Gift> get(){
        return giftService.getAllGift();
    }

    @GetMapping("/{id}")
    public Gift get(@PathVariable int id) {
        Gift gift = giftService.getGiftById(id);
        if(gift == null) {
            throw new RuntimeException("Employee not found for the Id:"+id);
        }
        return gift;
    }

    @PostMapping
    public Gift get(@RequestBody Gift gift) throws DAOException {
        giftService.addGift(gift);
        return gift;
    }

    @DeleteMapping("/{id}")
    public boolean deleteGift(@PathVariable int id) {
        return giftService.deleteGift(id);
    }



    @GetMapping(value = "/findbyname")
    public List<Gift> getGiftByName(@RequestParam(name="name",defaultValue = "")String name) throws NoEntityException {

        return giftService.findByName(name);
    }

    @GetMapping("/findbytag")
    public List<Gift> findGiftByTagName(@RequestParam("tagname")String tagName) throws NoEntityException {
        System.out.println(tagName);
        return giftService.findGiftByTagName(tagName);
    }

    @PutMapping("/changeprice/{id}")
    public Gift changePriceOfGift(@PathVariable("id") int id,@RequestParam("price")int newprice){
        return giftService.changePriceOfGift(id,newprice);
    }

    @GetMapping("/findbyseveraltags")
    public List<Gift> findGiftsBySeveralTags(@RequestParam("tag")List<String>tags) throws NoEntityException {
        System.out.println(tags);
        return giftService.findGiftBySeveralTags(tags);

    }

    @GetMapping("/mostpopulartag")
    public Tag findGiftsBySeveralTags() throws NoEntityException {
        return giftService.findMostPopulaarTag();

    }


}
