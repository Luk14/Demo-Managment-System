package me.lukasz.database.entities;

public class CarOrder
{

    private String orderID;
    private String carID;

    public CarOrder(String orderID, String carID)
    {
        this.orderID = orderID;
        this.carID = carID;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public String getCarID()
    {
        return carID;
    }

    public void setCarID(String carID)
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
