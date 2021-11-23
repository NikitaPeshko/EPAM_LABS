package com.epam.core;

import com.epam.peshko.StringUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {



    @Test
    void isAllPositive() {
        assertEquals(true, Utils.isAllPositive("10"));
    }
    @Test
    void isAllPositive1() {
        assertEquals(true, Utils.isAllPositive("10","20"));
    }
    @Test
    void isAllPositive2() {
        assertEquals(false, Utils.isAllPositive("10","-30"));
    }
    @Test
    void isAllPositive4() {
        assertEquals(false, Utils.isAllPositive("10","-30","50"));
    }
}