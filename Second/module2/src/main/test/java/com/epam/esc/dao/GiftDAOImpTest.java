package com.epam.esc.dao;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.entity.Gift;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GiftDAOImpTest {

    @Test
    void getGiftById() {
        RestTemplate restTemplate=new RestTemplate();
        GiftDTO gift=restTemplate.getForObject("http://localhost:8080/module2_war/gifts/1",GiftDTO.class);
        Set<String>set =new HashSet<>();
        set.add("spa");
        set.add("minsk");
        set.add("sport");
        GiftDTO expectedGift=new GiftDTO(1,"spagif","spa salon minsk nikita 123",10,7,"1970-01-19T23:06Z","1970-01-19T23:06Z",set);
        assertEquals(expectedGift.getName(),gift.getName());

    }

}