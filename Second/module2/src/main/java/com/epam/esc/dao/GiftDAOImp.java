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
public class GiftDAOImp implements GiftDAO{



    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftDAOImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<GiftDTO> getAllGifts() {
        return jdbcTemplate.query("SELECT *FROM gift_certificate",new GiftDTOMapper());
    }

    public List<GiftDTO> getAllGiftsSortByName(String sortingMethod) {
        List<GiftDTO> list = null;
        if(sortingMethod.equalsIgnoreCase("asc")){
            list=jdbcTemplate.query("SELECT *FROM gift_certificate order by gift_name asc",new GiftDTOMapper());
        }
        if(sortingMethod.equalsIgnoreCase("desc")){
            list=jdbcTemplate.query("SELECT *FROM gift_certificate order by gift_name desc",new GiftDTOMapper());
        }
        return list;
    }

    @Override
    public List<GiftDTO> getAllGiftsSortByDate(String sortingMethod) {
        List<GiftDTO> list = null;
        if(sortingMethod.equalsIgnoreCase("asc")){
            list=jdbcTemplate.query("SELECT *FROM gift_certificate order by create_date asc",new GiftDTOMapper());
        }
        if(sortingMethod.equalsIgnoreCase("desc")){
            list=jdbcTemplate.query("SELECT *FROM gift_certificate order by create_date desc",new GiftDTOMapper());
        }
        return list;
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

    @Override
    public boolean deleteGift(int id) {
        return jdbcTemplate.update("delete from gift_certificate where idgift_certificate=?",new Object[]{id})>0;

    }

    @Override
    public Gift updateGift(int id, Gift gift) {
        jdbcTemplate.update("update gift_certificate set gift_name=? ,discription=?," +
                "price=?,duration=? , create_date=? , last_update_date=? where idgift_certificate=?",
                new Object[]{gift.getName(),gift.getDiscription(),gift.getPrice(),
                gift.getDuration(),gift.getCreateDate(),gift.getLastUpdateDate(),id}   );

        return gift;
    }
}
