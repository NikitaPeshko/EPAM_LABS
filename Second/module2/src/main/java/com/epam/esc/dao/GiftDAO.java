package com.epam.esc.dao;


import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.exception.DaoException;
import com.epam.esc.model.Employee;
import com.epam.esc.model.Gift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class GiftDAO {



    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<GiftDTO> getAllGifts() {
        return jdbcTemplate.query("SELECT *FROM gift_certificate",new GiftDTOMapper());
    }

    public GiftDTO getGiftById(int id) {
        return jdbcTemplate.query("SELECT * FROM gift_certificate WHERE idgift_certificate=?", new Object[]{id}, new GiftDTOMapper())
                .stream().findAny().orElseThrow(()->new DaoException("no user by this id"));
    }

    public Gift addGift(Gift gift) {
        jdbcTemplate.update("INSERT INTO gift_certificate (gift_name, discription, price, duration, create_date, last_update_date)\n" +
                        "            VALUES(?, ?, ?, ?, ?, ?)",
                        gift.getName(),gift.getDiscription(),gift.getPrice(),gift.getDuration(),
                gift.getCreateDate(),gift.getLastUpdateDate());
        return gift;
    }





}
