package me.lukasz.utils;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class AuthenticateTest
{
    @Test
    public void hashPassword() throws NoSuchAlgorithmException
    {
        Authentication authentication = new Authentication("James#");
        assertEquals("1e7256423e3433441f82585001879395", authentication.hashPassword());
    }

}
