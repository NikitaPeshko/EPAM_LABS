package com.epam.peshko;

import static org.apache.commons.lang3.math.NumberUtils.createDouble;

public class StringUtil {
    public static void main(String[] args) {

    }
    public static boolean isPositiveNumber(String str) {

        Double number=0d;
        try {
            number=createDouble(str);
        } catch (NumberFormatException e){
            throw new RuntimeException("Not a number");

        }


        if (number>0)
            return true;
        else
            return false;

    }
}
