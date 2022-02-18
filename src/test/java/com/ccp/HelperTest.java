package com.ccp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HelperTest {

    @Mock
    Exception exception;

    @InjectMocks
    private Helper helper;

    @Test
    public void testCheckInputFile() {
        String[] inputArray = {"input.txt"};
        assertEquals(Helper.checkInputFile(inputArray), "input.txt");
    }

    @Test
    public void testCheckInputFileNotFoundReturnNull() {
        String[] inputArray = {"input1.txt"};
        assertNull(Helper.checkInputFile(inputArray));
    }

    @Test
    public void readFileNotFound() {
        Helper.readFile("dummyFile.txt");
        Mockito.verify(exception, Mockito.times(0)).printStackTrace();
    }

    @Test
    public void testLuhnValidationWithInValidCreditCardNumber() {
        assertFalse(Helper.luhnValidation("1234567890123456"));
    }

    @Test
    public void testLuhnValidationWithValidCreditCardNumber() {
        assertTrue(Helper.luhnValidation("4111111111111111"));
    }
}