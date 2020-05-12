package me.lukasz;

import me.lukasz.database.MySQLUtils;

import java.sql.Timestamp;

public class TeslaMain
{

    public static void main(String[] args)
    {
        MySQLUtils.connectDatabase();
    }

}
