package com.epam.esc.module3.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftCertificateController {

    @GetMapping("/gifts")
    public ResponseEntity getAllGifts(){
        try {
            return ResponseEntity.ok().body("All is ko with server");

        }catch (Exception e){
            return ResponseEntity.badRequest().body("No connection");
        }
    }
}
