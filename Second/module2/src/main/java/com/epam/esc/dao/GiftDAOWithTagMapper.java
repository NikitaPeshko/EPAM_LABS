package com.epam.esc.dao;

import com.epam.esc.DTO.GiftDTO;
import com.epam.esc.DTO.GiftDTOWithTag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GiftDAOWithTagMapper implements RowMapper<GiftDTOWithTag> {
    @Override
    public GiftDTOWithTag mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftDTOWithTag gift=new GiftDTOWithTag();
        gift.setId(resultSet.getInt("idgift_certificate"));
        gift.setGiftName(resultSet.getString("gift_name"));
        String tags=resultSet.getString(("ganre"));
        String[] tags1=tags.split(",");
       // gift.setTags((resultSet.getString("ganre")));
        Set<String> tagsSet=new HashSet<>(Arrays.asList(tags1));
        gift.setTags(tagsSet);
        return gift;
    }
}
