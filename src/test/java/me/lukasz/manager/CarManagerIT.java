package me.lukasz.manager;

import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.manager.CarManager;
import me.lukasz.database.manager.CustomerManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarManagerIT
{
    private Car car;
    private CarManager carManager;

    @Before
    public void init()
    {
        car = new Car("UID", "Model 3", Car.modelVersion.STANDARD, 12000, 2020, "Black", (float) 3.2, 202, 320);
        carManager = new CarManager();
    }

    @Test
    public void createReadTest()
    {
        Car car = new Car("UID", "Model 3", Car.modelVersion.STANDARD, 12000, 2020, "Black", (float) 3.2, 202, 320);
        carManager.createRecord(car);
        ArrayList<Object> carArrayList = carManager.getArrayObjects();
        Car newCreated = (Car) carArrayList.get(carArrayList.size()-1);
        assertEquals(car.getModel_name(), newCreated.getModel_name());
    }

    @Test
    public void updateReadTest()
    {
        ArrayList<Object> carArrayList = carManager.getArrayObjects();
        Car car = (Car) carArrayList.get(carArrayList.size()-1);
        carManager.setString(car.getId(), "model_name", "Model Y");
        Car updatedCar = (Car) carManager.getRecordObject(car.getId());
        assertEquals("Model Y", updatedCar.getModel_name());
    }

    @Test
    public void deleteTest()
    {
        ArrayList<Object> objectArrayList = carManager.getArrayObjects();
        Car toDelete = (Car) objectArrayList.get(objectArrayList.size()-1);
        carManager.deleteRecord(toDelete.getId());
        ArrayList<Object> updatedObjectList = carManager.getArrayObjects();
        Car lastIndexCar = (Car) updatedObjectList.get(updatedObjectList.size()-1);
        assertFalse(toDelete.getId().equals(lastIndexCar.getId()));
    }
}
