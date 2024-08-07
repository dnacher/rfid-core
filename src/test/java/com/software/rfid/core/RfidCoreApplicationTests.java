package com.software.rfid.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RfidCoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void test1() {
        assertEquals("ABC", "ABC");
        assertFalse("FirstName".length() == 10);
    }


    @Test
    public void test2() {
        String str4 = null;
        String str5 = "abc";

//        assertNotNull(str4);
//        assertNotNull(str5);
    }


    @Test
    public void test3() {
//        String str1 = "abc";
//        String str2 = "t23";
//        String str3 = "xyz";
//
//        assertAll("numbers",
//                () -> assertEquals(str1, "abc"),
//                () -> assertEquals(str2, "pqr"),
//                () -> assertEquals(str3, "xyz")
//        );
    }

}
