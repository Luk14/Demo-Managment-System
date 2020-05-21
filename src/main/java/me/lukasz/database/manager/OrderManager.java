package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.entities.Order;
import me.lukasz.utils.MsgUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderManager
{

    public void addOrder(Car[] car, Customer customer)
    {
        try
        {
            final String UUID = java.util.UUID.randomUUID().toString().substring(0, 8);
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
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void addOrder(Car car, Customer customer)
    {
        try
        {
            final String UUID = java.util.UUID.randomUUID().toString().substring(0, 8);
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO orders VALUES (?,?)");
            preparedStatement.setString(1, UUID);
            preparedStatement.setString(2, customer.getId());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = MySQL.getConnection().prepareStatement("INSERT INTO car_orders VALUES (?,?)");
            preparedStatement1.setString(1, UUID);
            preparedStatement1.setString(2, car.getId());
            preparedStatement1.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void addTooOrder(Car car, String orderID)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO car_orders VALUES (?, ?)");
            preparedStatement.setString(1, orderID);
            preparedStatement.setString(2, car.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }


    public void removeOrder(String orderID)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM orders WHERE OID = \"" + orderID + "\"");
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = MySQL.getConnection().prepareStatement("DELETE FROM car_orders WHERE OID = \"" + orderID + "\"");
            preparedStatement1.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public Order getOrder(String orderID)
    {
        Order order = new Order();
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT o.OID, o.CID, co.CRID FROM orders o JOIN car_orders co ON o.OID=co.OID  WHERE o.OID = \"" + orderID + "\"");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                order.setOrderID(resultSet.getString("OID"));
                order.setCustomerID(resultSet.getString("CID"));
                order.addCarID(resultSet.getString("CRID"));
            }
        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        return order;
    }

    public ArrayList<Order> getAllOrder()
    {
        ArrayList<Order> orders = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                orders.add(new Order(resultSet.getString("OID"), resultSet.getString("CID")));
            }
        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        return orders;
    }

    public double getOrderTotal(String orderID)
    {
        try
        {
            ArrayList<String> getCarIDs = new ArrayList<>();
            CarManager carManager = new CarManager();
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT o.OID, o.CID, co.CRID FROM orders o JOIN car_orders co ON o.OID=co.OID WHERE o.OID = \"" + orderID + "\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                getCarIDs.add(resultSet.getString("CRID"));
            }

            if (getCarIDs.size() == 1)
            {
                Car car = (Car) carManager.getRecordObject(getCarIDs.get(0));
                return car.getPrice();
            } else
            {
                double total = 0;
                for (String carID : getCarIDs)
                {
                    Car car = (Car) carManager.getRecordObject(carID);
                    total += car.getPrice();
                }
                return total;
            }
        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        return 0;
    }

}
