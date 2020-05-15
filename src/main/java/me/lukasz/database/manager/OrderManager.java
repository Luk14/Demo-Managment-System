package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.utils.Msg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderManager
{

        /*
    Add Order (Inc Customer/Car/CarOrder)
    Remove Order
    Update Order?
     */

    public void addOrder(Car[] car, Customer customer)
    {
        try
        {
            final String UUID = java.util.UUID.randomUUID().toString();
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO orders VALUES (?,?)");
            preparedStatement.setString(1, UUID);
            preparedStatement.setString(2, customer.getId());
            preparedStatement.executeUpdate();
            for (Car car1 : car)
            {
                PreparedStatement preparedStatement1 = MySQL.getConnection().prepareStatement("INSERT INTO car_orders VALUES (?,?)");
                preparedStatement1.setString(1, UUID);
                preparedStatement1.setString(2, car1.getId());
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void addOrder(Car car, Customer customer)
    {
        try
        {
            final String UUID = java.util.UUID.randomUUID().toString();
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO orders VALUES (?,?)");
            preparedStatement.setString(1, UUID);
            preparedStatement.setString(2, customer.getId());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = MySQL.getConnection().prepareStatement("INSERT INTO car_orders VALUES (?,?)");
            preparedStatement1.setString(1, UUID);
            preparedStatement1.setString(2, car.getId());
        } catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

}
