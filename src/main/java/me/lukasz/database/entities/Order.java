package me.lukasz.database.entities;

public class Order
{

    private String orderID;
    private String customerID;

    public Order(String orderID, String customerID)
    {
        this.orderID = orderID;
        this.customerID = customerID;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public String getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(String customerID)
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
