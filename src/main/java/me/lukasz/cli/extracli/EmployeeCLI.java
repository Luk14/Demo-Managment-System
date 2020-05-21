package me.lukasz.cli.extracli;

import me.lukasz.TeslaMain;
import me.lukasz.cli.MainCLI;
import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.EmployeeManager;
import me.lukasz.utils.MsgUtil;
import me.lukasz.utils.Scan;

import java.util.ArrayList;

public class EmployeeCLI extends MainCLI
{

    public void viewAllEmployees()
    {
        EmployeeManager employeeManager = new EmployeeManager();
        ArrayList<Object> arrayList = employeeManager.getArrayObjects();
        for (Object object : arrayList)
        {
            Employee employee = (Employee) object;
            printText(employee.toString());
        }
        sendExit();
    }

    public void addEmployee()
    {
        Employee employee = new Employee();
        boolean askagain = true;
        printText(" * Please Enter First Name:");
        employee.setFname(Scan.getString());
        printText(" * Please Enter Last Name:");
        employee.setLname(Scan.getString());
        printText(" * Please Enter User Permissions");
        do
        {
            try
            {
                employee.setPermissions(Employee.userPermission.valueOf(Scan.getString().toUpperCase()));
                askagain = false;
            } catch (Exception e)
            {
                printText(" * Wrong Value! Please Try Again!");
                askagain = true;
            }
        } while (askagain);
        //
        printText(" * Please Enter Work Sector");
        employee.setWork_sector(Scan.getString());
        printText(" * Please Enter Email");
        employee.setEmail(Scan.getString());
        printText(" * Please Enter Username");
        employee.setUsername(Scan.getString());
        printText(" * Please Enter Password");
        employee.setPassword(Scan.getString());
        printText("Here are the Employee Details, Reply Y to Create or N to Discard!");
        switch (Scan.getString().toLowerCase())
        {
            case "y":
                new EmployeeManager().createRecord(employee);
                printTextPrefix("Employee Created, Sending you back to Main Menu!");
                sendExit();
                break;
            case "n":
                printTextPrefix("Employee Creation Cancelled! Sending you back to Main Menu");
                sendExit();
                break;
            default:
                sendExit();
                break;
        }
    }

    public void updateEmployee()
    {
        EmployeeManager employeeManager = new EmployeeManager();
        Employee employee = null;
        boolean moreChange = true;
        printText("Please enter the UID of the User you wish to Edit!");
        final String UID = Scan.getString();
        if (employeeManager.getRecordObject(UID) == null)
        {
            printText("Employee UID not found! Sending back to Main Menu");
            this.optionsPerm(TeslaMain.employee);
        } else
        {
            employee = (Employee) employeeManager.getRecordObject(UID);
            do
            {
                printText("Use the Following Commands to Update Employee!");
                printText(" * FirstName <FirstName>");
                printText(" * LastName <LastName>");
                printText(" * UserPermissions <UserPermissions>");
                printText(" * WorkSector <WorkSector>");
                printText(" * Email <Email>");
                printText(" * Username <Username>");
                printText(" * Exit - Exit Employee Update Menu");
                String[] args = Scan.getString().split(" ");
                switch (args[0].toLowerCase())
                {
                    case "firstname":
                        employeeManager.setString(employee.getId(), "first_name", MsgUtil.joinString(args, args[0]));
                        break;
                    case "lastname":
                        employeeManager.setString(employee.getId(), "last_name", MsgUtil.joinString(args, args[0]));
                        break;
                    case "userpermissions":
                        Employee.userPermission temp = Employee.userPermission.STANDARD;
                        try
                        {
                            temp = Employee.userPermission.valueOf(Scan.getString().toUpperCase());
                        } catch (Exception e)
                        {
                            printText(" * Wrong Value! Please Try Again!");
                        }
                        employeeManager.setString(employee.getId(), "user_permissions", temp.toString());
                        break;
                    case "worksector":
                        employeeManager.setString(employee.getId(), "work_sector", MsgUtil.joinString(args, args[0]));
                        break;
                    case "email":
                        employeeManager.setString(employee.getId(), "email", MsgUtil.joinString(args, args[0]));
                        break;
                    case "username":
                        employeeManager.setString(employee.getId(), "username", MsgUtil.joinString(args, args[0]));
                        break;
                    case "exit":
                        moreChange = false;
                        break;
                }
            }
            while (moreChange);
            sendExit();
        }
    }

    public void deleteEmployee()
    {
        EmployeeManager employeeManager = new EmployeeManager();
        Employee employee;
        printText("Please enter the EID of the employee you wish to delete!");
        final String UID = Scan.getString();
        if (employeeManager.getRecordObject(UID) == null)
        {
            printText("Employee EID not found! Sending back to Main Menu");
            this.optionsPerm(TeslaMain.employee);
        } else
        {
            employee = (Employee) employeeManager.getRecordObject(UID);
            printText("Employee Found! Please review the Information and Confirm!");
            printText("");
            printText(employee.toString());
            printText("");
            printText("Type Y to delete Employee | Type N to discard and try again");
            switch (Scan.getString().toLowerCase())
            {
                case "y":
                    employeeManager.deleteRecord(employee.getId());
                    printText("Employee Deleted");
                    sendExit();
                    break;
                case "n":
                    this.deleteEmployee();
                    break;
                default:
                    sendExit();
            }
        }
    }

}
