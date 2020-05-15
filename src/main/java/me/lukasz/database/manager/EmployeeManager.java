package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.MySQLExec;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.entities.Employee;
import me.lukasz.utils.Authentication;
import me.lukasz.utils.Msg;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class EmployeeManager implements MySQLExec
{

    public void createRecord(String fname, String lname, Enum user_permissions, String work_sector, String email,String username, String password)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO employee (first_name, last_name, user_permissions, work_sector,email,username,password) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, lname);
            preparedStatement.setString(3, user_permissions.toString());
            preparedStatement.setString(4, work_sector);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, username);
            preparedStatement.setString(7, new Authentication(password).hashPassword());
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        }
        catch (SQLException | NoSuchAlgorithmException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void createRecord(Object object) throws NoSuchAlgorithmException
    {
        if(object instanceof Employee)
        {
            Employee employee = (Employee) object;
            createRecord(employee.getFname(), employee.getLname(), employee.getPermissions(), employee.getWork_sector(), employee.getEmail(), employee.getUsername(), employee.getPassword());
            System.out.println(Msg.EXECUTED_CORRECTLY);
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM employee WHERE EID = " + userId + ";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println(Msg.EXECUTED_CORRECTLY);
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                arrayList.add(new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), Employee.userPermission.valueOf(resultSet.getString(4).toUpperCase()),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }
        }
        catch (Exception e)
        {
            System.out.println(Msg.DB_ERROR);
        }
        System.out.println(Msg.EXECUTED_CORRECTLY);
        return arrayList;
    }

    public Object getRecordObject(int uniqueID)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM employee WHERE EID = " + uniqueID + ";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println(Msg.EXECUTED_CORRECTLY);
                return new Employee(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), Employee.userPermission.valueOf(resultSet.getString(4).toUpperCase()),
                        resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8));
            }
        }
        catch (Exception e)
        {
            System.out.println(Msg.DB_ERROR);
        }
        return null;
    }

    //TODO
    public void setString(String uniqueID, String targetField, String result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE employee SET " + targetField + " = " + result + " WHERE EID = " + uniqueID);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void setInt(String uniqueID, String targetField, int result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE employee SET " + targetField + " = " + result + " WHERE EID = " + uniqueID);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void deleteRecord(int uniquePK)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM employee WHERE EID = " + uniquePK);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        }
        catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

}
