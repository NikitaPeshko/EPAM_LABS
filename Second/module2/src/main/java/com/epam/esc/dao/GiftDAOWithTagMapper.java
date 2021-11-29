package com.epam.esc.dao;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.DTO.GiftDTOWithTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftDAOWithTagMapper implements RowMapper<GiftDTOWithTag> {
    @Override
    public GiftDTOWithTag mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftDTOWithTag gift=new GiftDTOWithTag();
        gift.setId(resultSet.getInt("idgift_certificate"));
        gift.setGiftName(resultSet.getString("gift_name"));
        gift.setTags(resultSet.getString("ganre"));
        return gift;
    }
}
