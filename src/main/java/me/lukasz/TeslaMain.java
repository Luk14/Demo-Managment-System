package me.lukasz;

import me.lukasz.cli.CliMain;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.EmployeeManager;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class TeslaMain
{

    public static Employee employee;

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException
    {
        MySQL.connectDatabase();
        CliMain cliMain = new CliMain();
        cliMain.mainCLI();
    }

}
