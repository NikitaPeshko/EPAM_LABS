package com.epam.core;


import com.epam.peshko.StringUtil;

public class Utils {

    public static boolean isAllPositive(String ...str){

        for (int i=0;i< str.length;i++) {
            if (!StringUtil.isPositiveNumber(str[i])){
                return false;
            }

        }
        return true;

    }

}
