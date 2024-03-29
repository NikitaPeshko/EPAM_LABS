package com.epam.esc.dao;

import com.epam.esc.entity.Gift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftMapper implements RowMapper<Gift> {
    @Override
    public Gift mapRow(ResultSet resultSet, int i) throws SQLException {
        Gift gift=new Gift();
        gift.setId(resultSet.getInt("idgift_certificate"));
        gift.setName(resultSet.getString("gift_name"));
        gift.setPrice(resultSet.getInt("price"));
        gift.setDuration(resultSet.getInt("duration"));
        gift.setDiscription(resultSet.getString("discription"));
        gift.setCreateDate(resultSet.getTimestamp("create_date"));
        gift.setLastUpdateDate(resultSet.getTimestamp("last_update_date"));
        return gift;
    }
}
