package com.ccp;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainCCPTest {

    @Test
    public void testMain() {
        MainCCP.main("input.txt");
        for (Record record : MainCCP.records){
            System.out.println(record);
        }
        assertEquals(3, MainCCP.records.size());
    }
}