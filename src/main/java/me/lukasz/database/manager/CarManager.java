package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.MySQLExec;
import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.utils.Msg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarManager implements MySQLExec
{

    public void createRecord(String model_name, Enum model_version, float price, short year, String colour, float zeroToSixty, int topSpeed, int range_wltp)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO Car (model_name, model_version, price, year, colour, zero_to_sixty, top_speed, range_wltp) VALUES (?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, model_name);
            preparedStatement.setString(2, model_version.toString());
            preparedStatement.setFloat(3, price);
            preparedStatement.setShort(4, year);
            preparedStatement.setString(5, colour);
            preparedStatement.setFloat(6, zeroToSixty);
            preparedStatement.setInt(7, topSpeed);
            preparedStatement.setInt(8, range_wltp);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void createRecord(Object object)
    {
        if (object instanceof Car)
        {
            Car car = (Car) object;
            createRecord(car.getModel_name(), car.getModel_version(), car.getPrice(), (short) car.getYear(), car.getColour(), car.getZeroToSixty(), (short) car.getTopSpeed(), (int) car.getRange_wltp());
            System.out.println(Msg.EXECUTED_CORRECTLY);
        } else
        {
            System.out.println(Msg.INVALID_OBJECT);
        }
    }

    public String getRecordString(int carId, String field)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM car WHERE CRID = " + carId + ";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println(Msg.EXECUTED_CORRECTLY);
                return resultSet.getString(field);
            }
        } catch (Exception e)
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM car");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                arrayList.add(new Car(resultSet.getInt(1), resultSet.getString(2), Car.modelVersion.valueOf(resultSet.getString(3).toUpperCase()), resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getString(6), resultSet.getFloat(7), resultSet.getShort(8), resultSet.getInt(9)));
            }
        } catch (Exception e)
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM car WHERE CRID = " + uniqueID + ";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                System.out.println(Msg.EXECUTED_CORRECTLY);
                return new Car(resultSet.getInt(1), resultSet.getString(2), Car.modelVersion.valueOf(resultSet.getString(3).toUpperCase()), resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getString(6), resultSet.getFloat(7), resultSet.getShort(8), resultSet.getInt(9));
            }
        } catch (Exception e)
        {
            System.out.println(Msg.DB_ERROR);
        }
        return null;
    }

    public void setString(String uniqueID, String targetField, String result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE car SET " + targetField + " = " + result + " WHERE CRID = " + uniqueID);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        } catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void setInt(String uniqueID, String targetField, int result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE car SET " + targetField + " = " + result + " WHERE CRID = " + uniqueID);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        } catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

    public void deleteRecord(int uniquePK)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM car WHERE CRID = " + uniquePK);
            preparedStatement.executeUpdate();
            System.out.println(Msg.EXECUTED_CORRECTLY);
        } catch (SQLException e)
        {
            System.out.println(Msg.DB_ERROR);
        }
    }

}
