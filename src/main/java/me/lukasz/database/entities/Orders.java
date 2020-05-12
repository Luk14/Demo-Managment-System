package me.lukasz.database.entities;

public class Orders
{

    private int orderID;
    private int customerID;

    public Orders(int orderID, int customerID)
    {
        this.orderID = orderID;
        this.customerID = customerID;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }
}
