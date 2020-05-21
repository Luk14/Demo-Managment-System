package me.lukasz.database.entities;

public class Car
{

    private String id;
    private String model_name;
    private modelVersion model_version;
    private float price;
    private float year;
    private String colour;
    private float zeroToSixty;
    private float topSpeed;
    private float range_wltp;

    public Car(String id, String model_name, modelVersion model_version, float price, float year, String colour, float zeroToSixty, float topSpeed, float range_wltp)
    {
        this.id = id;
        this.model_name = model_name;
        this.model_version = model_version;
        this.price = price;
        this.year = year;
        this.colour = colour;
        this.zeroToSixty = zeroToSixty;
        this.topSpeed = topSpeed;
        this.range_wltp = range_wltp;
    }

    public Car()
    {
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getModel_name()
    {
        return model_name;
    }

    public void setModel_name(String model_name)
    {
        this.model_name = model_name;
    }

    public Enum getModel_version()
    {
        return model_version;
    }

    public void setModel_version(modelVersion model_version)
    {
        this.model_version = model_version;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public float getYear()
    {
        return year;
    }

    public void setYear(float year)
    {
        this.year = year;
    }

    public String getColour()
    {
        return colour;
    }

    public void setColour(String colour)
    {
        this.colour = colour;
    }

    public float getZeroToSixty()
    {
        return zeroToSixty;
    }

    public void setZeroToSixty(float zeroToSixty)
    {
        this.zeroToSixty = zeroToSixty;
    }

    public float getTopSpeed()
    {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed)
    {
        this.topSpeed = topSpeed;
    }

    public float getRange_wltp()
    {
        return range_wltp;
    }

    public void setRange_wltp(float range_wltp)
    {
        this.range_wltp = range_wltp;
    }

    public enum modelVersion
    {
        STANDARD,
        LONG_RANGE,
        PREFORMANCE;
    }

    @Override
    public String toString()
    {
        return "Car: " +
                "ID:" + id +
                ", Model Name:'" + model_name + '\'' +
                ", Model Version:" + model_version +
                ", Price: £" + price +
                ", Year: " + year +
                ", Colour: " + colour + '\'' +
                ", Zero To Sixty: " + zeroToSixty +
                ", Top Speed: " + topSpeed +
                ", Range WLTP:" + range_wltp;
    }
}
