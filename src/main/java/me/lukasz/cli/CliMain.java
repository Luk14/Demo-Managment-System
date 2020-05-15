package me.lukasz.cli;

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
                printTextPrefix("Welcome to the System " + employee.getFname() + "!");
                printTextPrefix("Your Permissions are: " + employee.getPermissions());
                /*
                List Options!
                 */
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

    private void optionsPerm(Employee employee)
    {
        switch (employee.getPermissions())
        {
            case STANDARD:
                /*
                Add Order
                View Orders
                View Customers
                 */
                break;
            case ELEVATED:
                /*
                Remove Order
                Update Customer
                Update Order
                 */
                break;
            case ADMINISTRATOR:
                /*
                Add Employee
                Update Employee
                 */
                break;
            case SUPER_USER:
                /*
                Remove Employee
                 */
                break;
        }
    }
}
