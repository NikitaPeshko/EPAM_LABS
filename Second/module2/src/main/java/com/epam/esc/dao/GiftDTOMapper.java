package com.epam.esc.dao;

import com.epam.esc.DTO.GiftDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;


public class GiftDTOMapper implements RowMapper<GiftDTO> {


    @Override
    public GiftDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        GiftDTO gift=new GiftDTO();
        gift.setId(resultSet.getInt("idgift_certificate"));
        gift.setName(resultSet.getString("gift_name"));
        gift.setPrice(resultSet.getInt("price"));
        gift.setDuration(resultSet.getInt("duration"));
        gift.setDiscription(resultSet.getString("discription"));
     //   gift.setCreateDate(resultSet.getTimestamp("create_date"));
        Timestamp createdate=resultSet.getTimestamp("create_date");

        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        String nowAsISO = df.format(createdate);
        gift.setCreateDate(nowAsISO);

        Timestamp last_update_date=resultSet.getTimestamp("last_update_date");
        String nowAsISOlast_update_date = df.format(last_update_date);
        gift.setLastUpdateDate(nowAsISOlast_update_date);

        String tags=resultSet.getString(("tags"));
        String[] tags1=tags.split(",");
        Set<String> tagsSet=new HashSet<>(Arrays.asList(tags1));
        gift.setTags(tagsSet);


  //      gift.setLastUpdateDate(resultSet.getTimestamp("last_update_date"));
        return gift;
    }


}
