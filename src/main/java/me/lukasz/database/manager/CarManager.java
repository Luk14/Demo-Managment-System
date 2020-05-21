package me.lukasz.database.manager;

import me.lukasz.database.MySQL;
import me.lukasz.database.MySQLExec;
import me.lukasz.database.entities.Car;
import me.lukasz.utils.MsgUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class CarManager implements MySQLExec
{

    public void createRecord(String model_name, Enum model_version, float price, short year, String colour, float zeroToSixty, int topSpeed, int range_wltp)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("INSERT INTO Car (CRID, model_name, model_version, price, year, colour, zero_to_sixty, top_speed, range_wltp) VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, UUID.randomUUID().toString().substring(0, 8));
            preparedStatement.setString(2, model_name);
            preparedStatement.setString(3, model_version.toString());
            preparedStatement.setFloat(4, price);
            preparedStatement.setShort(5, year);
            preparedStatement.setString(6, colour);
            preparedStatement.setFloat(7, zeroToSixty);
            preparedStatement.setInt(8, topSpeed);
            preparedStatement.setInt(9, range_wltp);
            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void createRecord(Object object)
    {
        if (object instanceof Car)
        {
            Car car = (Car) object;
            createRecord(car.getModel_name(), car.getModel_version(), car.getPrice(), (short) car.getYear(), car.getColour(), car.getZeroToSixty(), (short) car.getTopSpeed(), (int) car.getRange_wltp());

        } else
        {
            System.out.println(MsgUtil.INVALID_OBJECT);
        }
    }

    public String getRecordString(String carId, String field)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM car WHERE CRID = \"" + carId + "\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {

                return resultSet.getString(field);
            }
        } catch (Exception e)
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM car");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                arrayList.add(new Car(resultSet.getString(1), resultSet.getString(2), Car.modelVersion.valueOf(resultSet.getString(3).toUpperCase()), resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getString(6), resultSet.getFloat(7), resultSet.getShort(8), resultSet.getInt(9)));
            }
        } catch (Exception e)
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
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("SELECT * FROM car WHERE CRID = \"" + uniqueID + "\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {

                return new Car(resultSet.getString(1), resultSet.getString(2), Car.modelVersion.valueOf(resultSet.getString(3).toUpperCase()), resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getString(6), resultSet.getFloat(7), resultSet.getShort(8), resultSet.getInt(9));
            }
        } catch (Exception e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
        return null;
    }

    public void setString(String uniqueID, String targetField, String result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE car SET " + targetField + " = \"" + result + "\" WHERE CRID = \"" + uniqueID + "\"");
            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void setInt(String uniqueID, String targetField, int result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE car SET " + targetField + " = " + result + " WHERE CRID = \"" + uniqueID + "\"");
            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void setDouble(String uniqueID, String targetField, double result)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("UPDATE car SET " + targetField + " = " + result + " WHERE CRID = \"" + uniqueID + "\"");
            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

    public void deleteRecord(String uniquePK)
    {
        try
        {
            PreparedStatement preparedStatement = MySQL.getConnection().prepareStatement("DELETE FROM car WHERE CRID = \"" + uniquePK + "\"");
            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(MsgUtil.DB_ERROR);
        }
    }

}
