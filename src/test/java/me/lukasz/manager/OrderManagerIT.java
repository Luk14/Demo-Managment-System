package me.lukasz.manager;

import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.entities.Order;
import me.lukasz.database.manager.OrderManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderManagerIT
{
    private Order order;
    private Car car;
    private Customer customer;
    private OrderManager orderManager;

    @Before
    public void init()
    {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("DLKFR34S");
        order = new Order("DFKGITOE", "FLGITDSS", cars);
        car = new Car("UID", "Model 3", Car.modelVersion.STANDARD, 12000, 2020, "Black", (float) 3.2, 202, 320);
        customer = new Customer("UID", "James", "Smith", (short) 25, "48 Chol Rd", "RM193DF", "Leeds", "JSmith@ICloud.com");
        orderManager = new OrderManager();
    }

    @Test
    public void createReadTest()
    {
        orderManager.addOrder(car, customer);
        ArrayList<Order> orderArrayList = orderManager.getAllOrder();
        Order newCreated = (Order) orderArrayList.get(orderArrayList.size() - 1);
        assertEquals(customer.getId(), newCreated.getCustomerID());
    }

    @Test
    public void updateReadTest()
    {
        ArrayList<Order> orderArrayList = orderManager.getAllOrder();
        Order order = orderArrayList.get(orderArrayList.size() - 1);
        orderManager.addTooOrder(car, order.getOrderID());
        Order updatedOrder = orderManager.getOrder(order.getOrderID());
        assertTrue(order.getCarIDs().size() != updatedOrder.getCarIDs().size());
    }

    @Test
    public void deleteTest()
    {
        ArrayList<Order> objectArrayList = orderManager.getAllOrder();
        Order toDelete = objectArrayList.get(objectArrayList.size() - 1);
        orderManager.removeOrder(toDelete.getOrderID());
        ArrayList<Order> updatedObjectList = orderManager.getAllOrder();
        Order lastIndexCar = updatedObjectList.get(updatedObjectList.size() - 1);
        assertFalse(toDelete.getOrderID().equals(lastIndexCar.getOrderID()));
    }
}
