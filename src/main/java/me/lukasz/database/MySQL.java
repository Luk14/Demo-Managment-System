package me.lukasz.database;

import me.lukasz.utils.Msg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MySQL
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
            System.out.println(Msg.DB_SUCCESS);
        }
        catch (SQLException e)
        {
            System.out.println(Msg.DB_UNSUCCESSFUL);
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
                System.out.println(Msg.DB_SUCCESS);
                return connection;
            }
        }
        catch (SQLException e)
        {
            System.out.println(Msg.DB_UNSUCCESSFUL);
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection()
    {
        try
        {
            getConnection().close();
        }
        catch (SQLException e)
        {
            System.out.println(Msg.PREFIX + " Error closing Database!");
        }
    }
}
