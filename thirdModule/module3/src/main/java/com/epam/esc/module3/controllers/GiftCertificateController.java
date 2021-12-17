package com.epam.esc.module3.controllers;


import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.entity.Tag;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.service.giftService.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GiftCertificateController {

    @Autowired
    private GiftServiceImpl giftService;

    @GetMapping("/gifts11111")
    public ResponseEntity registration(){
        try {
            return ResponseEntity.ok().body("All is ko with server");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("No connection");
        }
    }

    @GetMapping("/gifts")
    public List<Gift> get(){
        return giftService.getAllGift();
    }
    @GetMapping("/gifts/{id}")
    public Gift get(@PathVariable int id) {
        Gift gift = giftService.getGiftById(id);
        if(gift == null) {
            throw new RuntimeException("Employee not found for the Id:"+id);
        }
        return gift;
    }

    @PostMapping("/gifts")
    public Gift get(@RequestBody Gift gift) throws DAOException {
        giftService.addGift(gift);
        return gift;
    }

    @DeleteMapping("/gifts/{id}")
    public boolean deleteGift(@PathVariable int id) {
        return giftService.deleteGift(id);
    }



    @GetMapping(value = "/gifts/findbyname")
    public List<Gift> getGiftByName(@RequestParam(name="name",defaultValue = "")String name) {

        return giftService.findByName(name);
    }

    @GetMapping("/findbytag")
    public List<Gift> findGiftByTagName(@RequestParam("tagname")String tagName){
        System.out.println(tagName);
        return giftService.findGiftByTagName(tagName);
    }

    @PutMapping("/gifts/changeprice/{id}")
    public Gift changePriceOfGift(@PathVariable("id") int id,@RequestParam("price")int newprice){
        return giftService.changePriceOfGift(id,newprice);
    }

    @GetMapping("/gifts/gindbyseveraltags")
    public List<Gift> findGiftsBySeveralTags(@RequestParam("tag")List<String>tags){
        System.out.println(tags);
        return giftService.findGiftBySeveralTags(tags);

    }

    @GetMapping("/gifts/mostpopulartag")
    public Tag findGiftsBySeveralTags(){
        return giftService.findMostPopulaarTag();

    }


}
