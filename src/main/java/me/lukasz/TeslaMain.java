package me.lukasz;

import me.lukasz.cli.MainCLI;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Employee;

public class TeslaMain
{

    public static Employee employee;

    public static void main(String[] args)
    {
        MySQL.connectDatabase();
        MainCLI mainCLI = new MainCLI();
        mainCLI.mainCLI();
    }

}
