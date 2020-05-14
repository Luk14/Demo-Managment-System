package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.MySQLExec;
import me.lukasz.database.entities.Customer;
import me.lukasz.utils.Msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManager implements MySQLExec
{

    public void createRecord(String fname, String lname, short age, String address, String postcode, String city, String email)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO Customer (first_name, last_name, age, address,postcode, city, email) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setShort(3, age);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, postcode);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, email);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void createRecord(Object object)
    {
        if(object instanceof Customer)
        {
            Customer customer = (Customer) object;
            createRecord(customer.getFname(), customer.getLname(), customer.getAge(), customer.getAddress(), customer.getPostcode(), customer.getCity(),customer.getEmail());
        }
        else
        {
            System.out.println(Msg.INVALID_OBJECT);
        }
    }

    public String getRecordString(int userId, String field)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM customer WHERE CID = " + userId + ";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                return  resultSet.getString(field);
            }
        }
        catch (Exception e)
        {
            System.out.println(Msg.DB_ERROR);
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
                arrayList.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getShort(4),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        }
        catch (Exception e)
        {
            System.out.println(Msg.DB_ERROR);
        }
        return arrayList;
    }

    public Object getRecordObject(int uniqueID)
    {
        //Get Specific Object
        return null;
    }

    public void setString(String record, String targetField)
    {
        //Set Record String Value
    }

    public void setInt(String record, int targetField)
    {
        //Set Record int Value
    }

    public void setEnum(String record, Enum en)
    {
        //Set a Record with Enum Value
    }

    public void deleteRecord(int uniquePK)
    {
        //Delete Record
    }
}
