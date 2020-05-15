package me.lukasz.utils;

import me.lukasz.database.MySQL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication
{

    private String password;
    private String username;

    public Authentication(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Authentication(String password)
    {
        this.password = password;
    }

    public String hashPassword() throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public boolean authenticate() throws NoSuchAlgorithmException, SQLException
    {
        String localpass = "";
        PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM employee WHERE username = \"" + username + "\";");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
        {
            localpass = resultSet.getString(8);
        }
        return this.hashPassword().equals(localpass);
    }
}
