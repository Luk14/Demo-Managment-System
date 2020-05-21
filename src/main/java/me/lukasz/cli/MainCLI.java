package me.lukasz.cli;

import me.lukasz.TeslaMain;
import me.lukasz.cli.extracli.CarCLI;
import me.lukasz.cli.extracli.CustomerCLI;
import me.lukasz.cli.extracli.EmployeeCLI;
import me.lukasz.cli.extracli.OrderCLI;
import me.lukasz.database.MySQL;
import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.EmployeeManager;
import me.lukasz.utils.Authentication;
import me.lukasz.utils.Scan;
import me.lukasz.utils.UtilsCLI;

public class MainCLI extends UtilsCLI
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
            } else
            {
                printTextPrefix("Wrong Password/Username Please try again!\n");
                this.mainCLI();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            printTextPrefix("Error has occurred in the system, please try again!");
        }
    }

    //TODO
    public void optionsPerm(Employee employee)
    {
        switch (employee.getPermissions())
        {
            case SUPER_USER:
                printText("16] View All Orders");
                printText("15] Add Order");
                printText("14] Add Car To Order");
                printText("13] Remove Order");
            case ADMINISTRATOR:
                printText("12] Delete Employee");
                printText("11] Update Employee");
                printText("10] Add Employee");
                printText("9] View Employees");
            case ELEVATED:
                printText("8] Delete Car");
                printText("7] Update Car");
                printText("6] Add Car");
                printText("5] View Cars");
            case STANDARD:
                printText("4] Delete Customer");
                printText("3] Update Customer");
                printText("2] Add Customer");
                printText("1] View Customers");
                printText("0] Exit Application");
                break;
        }
        System.out.println("");
        int i = Scan.getInt();
        if (checkCorrectEntry(i, employee))
        {
            sendCLI(i);
        } else
        {
            printTextPrefix("Wrong Entry, please try again!");
            this.optionsPerm(employee);
        }
    }

    public void sendExit()
    {
        printText("Type: Home - Show Main Menu | Type: Exit - Quit Application");
        String input = Scan.getString();
        switch (input.toLowerCase())
        {
            case "home":
                printTextPrefix("Sending you to the main menu.");
                this.optionsPerm(TeslaMain.employee);
                break;
            case "exit":
                printText("Exiting Application");
                MySQL.closeConnection();
                System.exit(0);
            default:
                printTextPrefix("Invalid Option Selected! Sending back to Main Menu");
                optionsPerm(TeslaMain.employee);
        }
    }


    private void sendCLI(int input)
    {
        CustomerCLI customerCLI = new CustomerCLI();
        EmployeeCLI employeeCLI = new EmployeeCLI();
        CarCLI carCLI = new CarCLI();
        OrderCLI orderCLI = new OrderCLI();
        switch (input)
        {
            case 0:
                printTextPrefix("Exiting System!");
                MySQL.closeConnection();
                System.exit(0);
                break;
            case 1:
                customerCLI.viewAllCustomers();
                break;
            case 2:
                customerCLI.addCustomer();
                break;
            case 3:
                customerCLI.updateCustomer();
                break;
            case 4:
                customerCLI.deleteCustomer();
                break;
            case 5:
                carCLI.viewAllCars();
                break;
            case 6:
                carCLI.addCar();
                break;
            case 7:
                carCLI.updateCar();
                break;
            case 8:
                carCLI.deleteCar();
                break;
            case 9:
                employeeCLI.viewAllEmployees();
                break;
            case 10:
                employeeCLI.addEmployee();
                break;
            case 11:
                employeeCLI.updateEmployee();
                break;
            case 12:
                employeeCLI.deleteEmployee();
                break;
            case 13:
                orderCLI.deleteOrder();
                break;
            case 14:
                orderCLI.addCarToOrder();
                break;
            case 15:
                orderCLI.addOrder();
                break;
            default:
                printTextPrefix("Wrong Option Selected! Please retry");
                break;
        }
    }

    private boolean checkCorrectEntry(int i, Employee employee)
    {
        if (i <= 16 && employee.getPermissions().equals(Employee.userPermission.SUPER_USER)) return true;
        if (i <= 12 && employee.getPermissions().equals(Employee.userPermission.ADMINISTRATOR)) return true;
        if (i <= 8 && employee.getPermissions().equals(Employee.userPermission.ELEVATED)) return true;
        if (i <= 5 && employee.getPermissions().equals(Employee.userPermission.STANDARD)) return true;
        return false;
    }
}
