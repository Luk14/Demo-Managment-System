package me.lukasz;

import me.lukasz.cli.MainCLI;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.CarManager;
import me.lukasz.database.manager.CustomerManager;
import me.lukasz.database.manager.OrderManager;

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
