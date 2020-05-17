package me.lukasz.cli.othercli;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import me.lukasz.TeslaMain;
import me.lukasz.cli.CliMain;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Customer;
import me.lukasz.database.manager.CustomerManager;
import me.lukasz.utils.MsgUtil;
import me.lukasz.utils.Scan;
import org.omg.CORBA.CustomMarshal;

import java.util.ArrayList;

public class CustomerCli extends CliMain
{

    public void viewAllCustomers()
    {
        CustomerManager customerManager = new CustomerManager();
        ArrayList<Object> arrayList = customerManager.getArrayObjects();
        for (Object object : arrayList)
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

    public void updateCustomer()
    {
        CustomerManager customerManager = new CustomerManager();
        Customer customer = null;
        boolean moreChange = true;
        printText("Please enter the UID of the User you wish to Edit!");
        final String UID = Scan.getString();
        if (customerManager.getRecordObject(UID) == null)
        {
            printText("Customer UID not found! Sending back to Main Menu");
            this.optionsPerm(TeslaMain.employee);
        } else
        {
            customer = (Customer) customerManager.getRecordObject(UID);
            do
            {
                printText("Use the Following Commands to Update Customer!");
                printText(" * FirstName <Name>");
                //More
                String[] args = Scan.getString().split(" ");
                switch (args[0])
                {
                    case "FirstName":
                        customerManager.setString(customer.getId(), "first_name", MsgUtil.joinString(args, args[0]));
                }
            }
            while (moreChange);
        }
    }
}
