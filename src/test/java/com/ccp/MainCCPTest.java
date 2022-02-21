package com.ccp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainCCPTest {

    @Test
    public void testMain() {
        MainCCP.main("input.txt");
        assertEquals(3, MainCCP.records.size());
    }

    @Test
    public void testMainWithSecondFile() {
        MainCCP.main("src/main/resources/input2.txt");
        assertEquals(1, MainCCP.records.stream().filter(r -> r.getName().equals("Mathew") && r.getStatus().equals("error")).count());
    }

    @Test
    public void testMainWithThirdFile() {
        MainCCP.main("src/main/resources/input3.txt");
        assertEquals(1, MainCCP.records.stream().filter(r -> r.getName().equals("Monica") && r.getBalance() == -2993).count());
    }
}