package me.lukasz.database.entities;

import java.util.ArrayList;

public class Order
{

    private String orderID;
    private String customerID;
    private ArrayList<String> carIDs = new ArrayList<>();

    public Order(String orderID, String customerID, ArrayList<String> carIDs)
    {
        this.orderID = orderID;
        this.customerID = customerID;
        this.carIDs = carIDs;
    }

    public Order(String orderID, String customerID)
    {
        this.orderID = orderID;
        this.customerID = customerID;
    }

    public Order(){}

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

    public void addCarID(String carID)
    {
        getCarIDs().add(carID);
    }

    public void removeCarID(String carID)
    {
        getCarIDs().remove(carID);
    }

    public String getCarIDsString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for(String string : getCarIDs())
        {
            stringBuilder.append(string + ",");
        }
        stringBuilder.replace(stringBuilder.length()-1, stringBuilder.length(), "");
        return stringBuilder.toString();
    }

    public ArrayList<String> getCarIDs()
    {
        return carIDs;
    }

    public void setCarIDs(ArrayList<String> carIDs)
    {
        this.carIDs = carIDs;
    }

    public String toStringNoCar()
    {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                '}';
    }

    @Override
    public String toString()
    {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", carIDs=" + getCarIDsString() +
                '}';
    }
}
