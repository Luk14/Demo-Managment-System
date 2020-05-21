package me.lukasz.manager;

import me.lukasz.database.entities.Customer;
import me.lukasz.database.manager.CustomerManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CustomerManagerIT
{
    private Customer customer;
    private CustomerManager customerManager;

    @Before
    public void init()
    {
        customer = new Customer("UID", "James", "Smith", (short) 25, "48 Chol Rd", "RM193DF", "Leeds", "JSmith@ICloud.com");
        customerManager = new CustomerManager();
    }

    @Test
    public void createReadTest()
    {
        Customer customer = new Customer("UID", "James", "Smith", (short) 25, "48 Chol Rd", "RM193DF", "Leeds", "JSmith@ICloud.com");
        customerManager.createRecord(customer);
        ArrayList<Object> customerArrayList = customerManager.getArrayObjects();
        Customer newCreated = (Customer) customerArrayList.get(customerArrayList.size() - 1);
        assertEquals(customer.getFname(), newCreated.getFname());
    }

    @Test
    public void updateReadTest()
    {
        ArrayList<Object> customerArrayList = customerManager.getArrayObjects();
        Customer customer = (Customer) customerArrayList.get(customerArrayList.size() - 1);
        customerManager.setString(customer.getId(), "first_name", "Smith");
        Customer updatedCustomer = (Customer) customerManager.getRecordObject(customer.getId());
        assertEquals("Smith", updatedCustomer.getFname());
    }

    @Test
    public void deleteTest()
    {
        ArrayList<Object> objectArrayList = customerManager.getArrayObjects();
        Customer toDelete = (Customer) objectArrayList.get(objectArrayList.size() - 1);
        customerManager.deleteRecord(toDelete.getId());
        ArrayList<Object> updatedObjectList = customerManager.getArrayObjects();
        Customer lastIndexCustomer = (Customer) updatedObjectList.get(updatedObjectList.size() - 1);
        assertFalse(toDelete.getId().equals(lastIndexCustomer.getId()));
    }

}
