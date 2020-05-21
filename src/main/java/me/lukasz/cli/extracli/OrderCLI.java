package me.lukasz.cli.extracli;

import me.lukasz.cli.MainCLI;
import me.lukasz.database.entities.Car;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.entities.Order;
import me.lukasz.database.manager.CarManager;
import me.lukasz.database.manager.CustomerManager;
import me.lukasz.database.manager.OrderManager;
import me.lukasz.utils.Scan;

public class OrderCLI extends MainCLI
{

    public void addCarToOrder()
    {
        OrderManager orderManager = new OrderManager();
        CarManager carManager = new CarManager();
        String orderID = "";
        String carID = "";
        printText("Please enter the Order ID you wish to add another Car too");
        orderID = Scan.getString();
        printText("Please enter the Car ID you wish add to the Order: " + orderID);
        carID = Scan.getString();
        if (carManager.getRecordObject(carID) == null)
        {
            printText("Invalid ID! Please try again");
            this.addCarToOrder();
            return;
        }
        orderManager.addTooOrder((Car) carManager.getRecordObject(carID), orderID);
        sendExit();
    }

    public void addOrder()
    {
        OrderManager orderManager = new OrderManager();
        CustomerManager customerManager = new CustomerManager();
        CarManager carManager = new CarManager();
        String carID = "";
        String customerID = "";
        printText("Please enter the Car ID you wish to add to the Order!");
        carID = Scan.getString();
        if (carManager.getRecordObject(carID) == null)
        {
            printText("Invalid ID! Please try again!");
            this.addOrder();
            return;
        }
        printText("Please enter the Customer ID associated with the Order!");
        customerID = Scan.getString();
        if (customerManager.getRecordObject(customerID) == null)
        {
            printText("Invalid ID! Please try again!");
            this.addOrder();
            return;
        }
        printText("Creating Order...");
        try
        {
            orderManager.addOrder((Car) carManager.getRecordObject(carID), (Customer) customerManager.getRecordObject(customerID));
        } catch (Exception e)
        {
            printText("Oops, something went wrong, please try again!");
            this.addOrder();
        }
        printText("Order Created! Please select an option to continue!");
        sendExit();
    }

    public void deleteOrder()
    {
        OrderManager orderManager = new OrderManager();
        printText("Please enter the OID you wish to delete!");
        final String OID = Scan.getString();
        printText("Order Found! Please review the Information and Confirm!");
        printText("");
        printText("Order ID: " + OID);
        printText("");
        printText("Type Y to delete Order | Type N to discard and try again");
        switch (Scan.getString().toLowerCase())
        {
            case "y":
                orderManager.removeOrder(OID);
                printText("Order Deleted | Sending back to Main Menu");
                sendExit();
                break;
            case "n":
                this.deleteOrder();
                break;
            default:
                sendExit();
        }
    }

    public void viewAllOrders()
    {
        OrderManager orderManager = new OrderManager();
        for (Order order : orderManager.getAllOrder())
        {
            System.out.println(orderManager.getOrder(order.getOrderID()).toString() + " Total: " + orderManager.getOrderTotal(order.getOrderID()));
        }
        sendExit();
    }

}
