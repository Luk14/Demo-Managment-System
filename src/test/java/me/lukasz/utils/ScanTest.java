package me.lukasz.utils;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ScanTest
{
    @Rule
    public TextFromStandardInputStream sysinMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test
    public void testUserInput()
    {
        Scan scan = mock(Scan.class);
        //Strings
        sysinMock.provideLines("James");
        assertEquals("James", scan.getString());
        sysinMock.provideLines("Sam Jones");
        assertEquals("Sam Jones", scan.getString());
        sysinMock.provideLines("PERFORMANCE");
        assertEquals("PERFORMANCE", scan.getString());
        //Ints
        sysinMock.provideLines("5");
        assertEquals(5, scan.getInt());
        sysinMock.provideLines("5200");
        assertEquals(5200, scan.getInt());
        sysinMock.provideLines("88998");
        assertEquals(88998, scan.getInt());
        //Doubles
        sysinMock.provideLines("10.5");
        assertEquals(10.5, scan.getDouble(),0);
        sysinMock.provideLines("253.22");
        assertEquals(253.22, scan.getDouble(),0);
        sysinMock.provideLines("1235.22");
        assertEquals(1235.22, scan.getDouble(),0);
    }
}
