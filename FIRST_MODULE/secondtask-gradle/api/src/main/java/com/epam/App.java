package com.epam;

import com.epam.core.Utils;


public class App {
    public static void main(String[] args) {

        System.out.println(Utils.isAllPositive("10","20"));
        System.out.println(Utils.isAllPositive("10","20","-24"));
    }
}
