package me.lukasz.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MsgUtilTest
{
    MsgUtil msgUtil;

    @Before
    public void before()
    {
        msgUtil = mock(MsgUtil.class);
    }

    @Test
    public void joinStringTest()
    {
        String[] tempString = {"This", "Message", "Test"};
        assertEquals("This Message Test", msgUtil.joinString(tempString));
    }

    @Test
    public void joinStringRemoveArgTest()
    {
        String[] tempString = {"This", "Message", "Test"};
        assertEquals("This Message ", msgUtil.joinString(tempString, "Test"));
    }

    @Test
    public void isNumberTest()
    {
        String number = "50";
        assertTrue(msgUtil.isNumber(number));
        String notNumber = "Number 12";
        assertFalse(msgUtil.isNumber(notNumber));
    }
}
