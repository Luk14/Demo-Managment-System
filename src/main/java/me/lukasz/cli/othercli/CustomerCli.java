package me.lukasz.cli.othercli;

import me.lukasz.TeslaMain;
import me.lukasz.cli.CliMain;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.manager.CustomerManager;
import me.lukasz.utils.Scan;

import java.util.ArrayList;

public class CustomerCli extends CliMain
{

    public void viewAllCustomers()
    {
        CustomerManager customerManager = new CustomerManager();
        ArrayList<Object> arrayList = customerManager.getArrayObjects();
        for(Object object : arrayList)
        {
            Customer customer = (Customer) object;
            printText(customer.toString());
        }
        printText("\nType: Home for Main Screen | Type: Exit to close Application");
        String s = Scan.getString().toLowerCase();
        switch (s)
        {
            case "home":
                this.optionsPerm(TeslaMain.employee);
                break;
            case "exit":
                printTextPrefix("Exiting Management System!");
                MySQL.closeConnection();
                System.exit(0);
            default:
                printTextPrefix("Error");
        }
    }

    // public void createRecord(String fname, String lname, short age, String address, String postcode, String city, String email)
    public void addCustomer()
    {
        Customer customer = new Customer();
        printText("Please Enter First Name:");
        customer.setFname(Scan.getString());
        printText("Please Enter Last Name:");
        customer.setLname(Scan.getString());
        printText("Please Enter Age");
        customer.setAge((short) Scan.getInt());
        printText("Please Enter Address");
        customer.setAddress(Scan.getString());
        printText("Please Enter Postcode");
        customer.setPostcode(Scan.getString());
        printText("Please Enter City");
        customer.setCity(Scan.getString());
        printText("Please Enter Email");
        customer.setEmail(Scan.getString());
        printText("Here are the Customer Details, Reply Y to Create or N to Discard!");
        switch (Scan.getString().toLowerCase())
        {
            case "y":
                new CustomerManager().createRecord(customer);
                printTextPrefix("Customer Created, Sending you back to Main Menu!");
                this.optionsPerm(TeslaMain.employee);
                break;
            case "n":
                printTextPrefix("Customer Creation Cancelled! Sending you back to Main Menu");
                this.optionsPerm(TeslaMain.employee);
                break;
            default:
                printTextPrefix("Invalid Input! Sending you back to Main Menu");
                this.optionsPerm(TeslaMain.employee);
                break;
        }
    }
}
