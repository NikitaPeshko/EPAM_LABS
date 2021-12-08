package com.epam.esc.module3.controllers;


import com.epam.esc.module3.entity.Gift;
import com.epam.esc.module3.exception.DAOException;
import com.epam.esc.module3.service.GiftServiceImpl;
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


}
