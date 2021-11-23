package com.epam.peshko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void isPositiveNumber() {
        assertEquals(true, StringUtil.isPositiveNumber("20"));
    }
    @Test
    void isPositiveNumber1() {
        assertEquals(false, StringUtil.isPositiveNumber("-20"));
    }
    @Test
    void isPositiveNumber2() {
        assertThrows(RuntimeException.class , ()->{
            StringUtil.isPositiveNumber("one");});

    }
}