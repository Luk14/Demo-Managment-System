package me.lukasz.database.entities;

public class Order
{

    private int orderID;
    private int customerID;

    public Order(int orderID, int customerID)
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

    @Override
    public String toString()
    {
        return "Order{" +
                "orderID=" + orderID +
                ", customerID=" + customerID +
                '}';
    }
}
