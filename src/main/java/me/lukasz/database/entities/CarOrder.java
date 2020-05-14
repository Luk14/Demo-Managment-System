package me.lukasz.database.entities;

public class CarOrder
{

    private int orderID;
    private int carID;

    public CarOrder(int orderID, int carID)
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

    @Override
    public String toString()
    {
        return "CarOrder{" +
                "orderID=" + orderID +
                ", carID=" + carID +
                '}';
    }
}
