package me.lukasz.cli;

import me.lukasz.TeslaMain;
import me.lukasz.cli.othercli.CustomerCli;
import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.EmployeeManager;
import me.lukasz.utils.Authentication;
import me.lukasz.utils.CliUtil;
import me.lukasz.utils.Msg;
import me.lukasz.utils.Scan;

public class CliMain extends CliUtil
{

    public void mainCLI()
    {
        /*
        Login View
         */
        printTextPrefix("Welcome to the Tesla Management Inventory System! Please login!");
        printText("Enter your Username:");
        String username = Scan.getString();
        printText("Enter your Password");
        String password = Scan.getString();
        Authentication authentication = new Authentication(username, password);
        try
        {
            if (authentication.authenticate())
            {
                Employee employee = (Employee) new EmployeeManager().getRecordObjectUsername(username);
                TeslaMain.employee = employee;
                printTextPrefix("Welcome to the System " + employee.getFname() + "!");
                printTextPrefix("Your Permissions are: " + employee.getPermissions() + "\n");
                optionsPerm(employee);
            }
            else
            {
                printTextPrefix("Wrong Password/Username Please try again!\n");
                this.mainCLI();
            }
        }
        catch (Exception e)
        {
            printTextPrefix("Error has occurred in the system, please try again!");
        }
    }

    public void optionsPerm(Employee employee)
    {
        switch (employee.getPermissions())
        {
            case SUPER_USER:
                printText("10] Remove Employee");
            case ADMINISTRATOR:
                printText("9] Add Employee");
                printText("8] Update Employee");
            case ELEVATED:
                printText("7] Remove Order");
                printText("6] Update Customer Details");
                printText("5] Update Order");
            case STANDARD:
                printText("4] Add Order");
                printText("3] View Orders");
                printText("2] Add Customer");
                printText("1] View Customers"); //Done
                break;
        }
        System.out.println("");
        int i = Scan.getInt();
        if(checkCorrectEntry(i, employee))
        {
            sendCLI(i);
        }
        else
        {
            printTextPrefix("Wrong Entry, please try again!");
            this.optionsPerm(employee);
        }
    }

    private void sendCLI(int input)
    {
        CustomerCli customerCli = new CustomerCli();
        switch (input)
        {
            case 1:
                customerCli.viewAllCustomers();
                break;
            case 2:
                customerCli.addCustomer();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                printTextPrefix("Wrong Option Selected! Please retry");
                break;
        }
    }

    private boolean checkCorrectEntry(int i, Employee employee)
    {
        if(i <= 10 && employee.getPermissions().equals(Employee.userPermission.SUPER_USER))return true;
        if(i <= 9 && employee.getPermissions().equals(Employee.userPermission.ADMINISTRATOR))return true;
        if(i <= 7 && employee.getPermissions().equals(Employee.userPermission.ELEVATED))return true;
        if(i <= 4 && employee.getPermissions().equals(Employee.userPermission.STANDARD))return true;
        return false;
    }
}
