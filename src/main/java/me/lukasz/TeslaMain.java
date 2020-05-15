package me.lukasz;

import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.CarManager;
import me.lukasz.database.manager.EmployeeManager;
import me.lukasz.utils.Authentication;
import org.omg.CORBA.OBJ_ADAPTER;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class TeslaMain
{

    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException
    {
        MySQL.connectDatabase();
        CarManager carManager = new CarManager();
        //carManager.createRecord("Model 3", Car.modelVersion.PREFORMANCE, 5862.99f, (short) 2020, "Black", 3.2f,  178, 380);
        for(Object object : carManager.getArrayObjects())
        {
            Car car = (Car) object;
            System.out.println(car.toString());
        }
        Car car = (Car) carManager.getRecordObject(1);
        System.out.println(car.getModel_name());

    }

}
