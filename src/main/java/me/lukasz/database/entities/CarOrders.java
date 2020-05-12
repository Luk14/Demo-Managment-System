package me.lukasz.database.entities;

public class CarOrders
{

    private int orderID;
    private int carID;

    public CarOrders(int orderID, int carID)
    {
        this.orderID = orderID;
        this.carID = carID;
    }

    public int getOrderID()
    {
        return orderID;
    }

    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    public int getCarID()
    {
        return carID;
    }

    public void setCarID(int carID)
    {
        this.carID = carID;
    }
}
