package me.lukasz;

import me.lukasz.cli.MainCLI;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Employee;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TeslaMain
{

    public static Employee employee;

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException
    {
        MySQL.connectDatabase();
        MainCLI mainCLI = new MainCLI();
        mainCLI.mainCLI();
    }

}
