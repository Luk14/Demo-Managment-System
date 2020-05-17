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

    public void addCustomer()
    {
        Customer customer = new Customer();
        printText(" * Please Enter First Name:");
        customer.setFname(Scan.getString());
        printText(" * Please Enter Last Name:");
        customer.setLname(Scan.getString());
        printText(" * Please Enter Age");
        customer.setAge((short) Scan.getInt());
        printText(" * Please Enter Address");
        customer.setAddress(Scan.getString());
        printText(" * Please Enter Postcode");
        customer.setPostcode(Scan.getString());
        printText(" * Please Enter City");
        customer.setCity(Scan.getString());
        printText(" * Please Enter Email");
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
                printText(" * FirstName <FirstName>");
                printText(" * LastName <LastName>");
                printText(" * Age <Age>");
                printText(" * Address <Address>");
                printText(" * Postcode <Postcode>");
                printText(" * City <City>");
                printText(" * Email <Email>");
                printText(" * Exit - Exit Customer Update Menu");
                String[] args = Scan.getString().split(" ");
                switch (args[0].toLowerCase())
                {
                    case "firstname":
                        customerManager.setString(customer.getId(), "first_name", MsgUtil.joinString(args, args[0]));
                        break;
                    case "lastname":
                        customerManager.setString(customer.getId(), "last_name", MsgUtil.joinString(args, args[0]));
                        break;
                    case "age":
                        if(MsgUtil.isNumber(args[1]))
                        {
                            customerManager.setInt(customer.getId(), "age", Integer.parseInt(args[1]));
                        }
                        else
                        {
                            printText("Wrong Input, a Number is Required!");
                        }
                        break;
                    case "address":
                        customerManager.setString(customer.getId(), "address", MsgUtil.joinString(args, args[0]));
                        break;
                    case "city":
                        customerManager.setString(customer.getId(), "city", MsgUtil.joinString(args, args[0]));
                        break;
                    case "postcode":
                        customerManager.setString(customer.getId(), "postcode", MsgUtil.joinString(args, args[0]));
                        break;
                    case "email":
                        customerManager.setString(customer.getId(), "email", MsgUtil.joinString(args, args[0]));
                        break;
                    case "exit":
                        moreChange = false;
                        break;
                }
            }
            while (moreChange);
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
                    break;
                default:
                    printTextPrefix("Error has occurred, Sending back to Main Menu");
                    this.optionsPerm(TeslaMain.employee);
            }
        }
    }

    public void deleteCustomer()
    {
        CustomerManager customerManager = new CustomerManager();
        Customer customer;
        printText("Please enter the UID of the customer you wish to delete!");
        final String UID = Scan.getString();
        if (customerManager.getRecordObject(UID) == null)
        {
            printText("Customer UID not found! Sending back to Main Menu");
            this.optionsPerm(TeslaMain.employee);
        } else
        {
            customer = (Customer) customerManager.getRecordObject(UID);
            printText("Customer Found! Please review the Information and Confirm!");
            printText("");
            printText(customer.toString());
            printText("");
            printText("Type Y to delete Customer | Type N to discard and try again | Type Exit to go back to Main Menu");
            switch (Scan.getString().toLowerCase())
            {
                case "y":
                    customerManager.deleteRecord(customer.getId());
                    printText("Customer Deleted | Sending back to Main Menu");
                    this.optionsPerm(TeslaMain.employee);
                    break;
                case "n":
                    this.deleteCustomer();
                    break;
                case "exit":
                    this.optionsPerm(TeslaMain.employee);
                    break;
            }
        }
    }
}
