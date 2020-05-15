package me.lukasz;

import me.lukasz.database.MySQL;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TeslaMain
{

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException
    {
        MySQL.connectDatabase();

    }

}
