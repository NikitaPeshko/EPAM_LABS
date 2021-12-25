package com.epam.esc.module3.dto;

import javax.persistence.Column;
import java.sql.Timestamp;

public class GiftDTO {
    private int gift_id;

    private String name;

    private String discription;

    private int price;

    private int duration;

    private Timestamp createDate;

    private Timestamp lastUpdateDate;
}
