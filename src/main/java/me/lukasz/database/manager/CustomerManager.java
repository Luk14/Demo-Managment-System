package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.MySQLExec;
import me.lukasz.database.entities.Customer;
import me.lukasz.utils.MsgUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class CustomerManager implements MySQLExec
{

    public void createRecord(String fname, String lname, short age, String address, String postcode, String city, String email)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO Customer (CID, first_name, last_name, age, address,postcode, city, email) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, UUID.randomUUID().toString().substring(0, 8));
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setShort(4, age);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, postcode);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, email);
            preparedStatement.executeUpdate();
            System.out.println(MsgUtil.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void createRecord(Object object)
    {
        if(object instanceof Customer)
        {
            Customer customer = (Customer) object;
            createRecord(customer.getFname(), customer.getLname(), customer.getAge(), customer.getAddress(), customer.getPostcode(), customer.getCity(),customer.getEmail());
            System.out.println(MsgUtil.EXECUTED_CORRECTLY);
        }
        else
        {
            System.out.println(MsgUtil.INVALID_OBJECT);
        }
    }

    public String getRecordString(String userId, String field)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM customer WHERE CID = \"" + userId + "\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println(MsgUtil.EXECUTED_CORRECTLY);
                return  resultSet.getString(field);
            }
        }
        catch (Exception e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        return null;
    }

    public ArrayList<Object> getArrayObjects()
    {
        ArrayList<Object> arrayList = new ArrayList<>();
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                arrayList.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getShort(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        }
        catch (Exception e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        System.out.println(MsgUtil.EXECUTED_CORRECTLY);
        return arrayList;
    }

    public Object getRecordObject(String uniqueID)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM customer WHERE CID = \"" + uniqueID + "\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println(MsgUtil.EXECUTED_CORRECTLY);
                return new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getShort(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
            }
        }
        catch (Exception e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        return null;
    }

    public void setString(String uniqueID, String targetField, String result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE customer SET " + targetField + " = \"" + result + "\" WHERE CID = \"" + uniqueID + "\"");
            preparedStatement.executeUpdate();
            System.out.println(MsgUtil.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void setInt(String uniqueID, String targetField, int result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE customer SET " + targetField + " = " + result + " WHERE CID = \"" + uniqueID + "\"");
            preparedStatement.executeUpdate();
            System.out.println(MsgUtil.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void deleteRecord(String uniquePK)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM customer WHERE CID = \"" + uniquePK + "\"");
            preparedStatement.executeUpdate();
            System.out.println(MsgUtil.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }
}
