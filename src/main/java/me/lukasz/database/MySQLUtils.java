package me.lukasz.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQLUtils
{

    public static final String HOST = "localhost";
    public static final String PORT = "3306";
    public static final String DATABASE = "tesla";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

    public static Connection connection;

    public static void connectDatabase()
    {
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?serverTimezone=UTC", USER, PASSWORD);
            System.out.println("[TeslaManagment] Connection has been established to the Database!");
        }
        catch (SQLException e)
        {
            System.out.println("[TeslaManagment] Error has occured in connection with the database!\n Stacktrace: ");
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        try
        {
            if(connection==null||connection.isClosed())
            {
                connectDatabase();
                return connection;
            }
        }
        catch (SQLException e)
        {
            System.out.println("[TeslaManagment] Error has occured in connection with the database!\n Stacktrace: ");
            e.printStackTrace();
        }
        return connection;
    }

    public static boolean isConnected()
    {
        return (connection != null);
    }

}
