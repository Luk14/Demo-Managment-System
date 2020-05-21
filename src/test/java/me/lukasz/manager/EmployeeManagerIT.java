package me.lukasz.manager;

import me.lukasz.database.entities.Employee;
import me.lukasz.database.manager.EmployeeManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmployeeManagerIT
{
    private Employee employee;
    private EmployeeManager employeeManager;

    @Before
    public void init()
    {
        employee = new Employee("UID", "Sam", "Smith", Employee.userPermission.STANDARD, "Buisness", "sam.smith@Icloud.com", "sam.smith", "smithy");
        employeeManager = new EmployeeManager();
    }

    @Test
    public void createReadTest()
    {
        Employee employee = new Employee("UID", "Sam", "Smith", Employee.userPermission.STANDARD, "Buisness", "sam.smith@Icloud.com", "sam.smith", "smithy");
        employeeManager.createRecord(employee);
        ArrayList<Object> employeeArrayList = employeeManager.getArrayObjects();
        Employee newCreated = (Employee) employeeArrayList.get(employeeArrayList.size() - 1);
        assertEquals(employee.getFname(), newCreated.getFname());
    }

    @Test
    public void updateReadTest()
    {
        ArrayList<Object> employeeArrayList = employeeManager.getArrayObjects();
        Employee employee = (Employee) employeeArrayList.get(employeeArrayList.size() - 1);
        employeeManager.setString(employee.getId(), "first_name", "Jim");
        Employee updatedEmployee = (Employee) employeeManager.getRecordObject(employee.getId());
        assertEquals("Jim", updatedEmployee.getFname());
    }

    @Test
    public void deleteTest()
    {
        ArrayList<Object> objectArrayList = employeeManager.getArrayObjects();
        Employee toDelete = (Employee) objectArrayList.get(objectArrayList.size() - 1);
        employeeManager.deleteRecord(toDelete.getId());
        ArrayList<Object> updatedObjectList = employeeManager.getArrayObjects();
        Employee lastIndexEmployee = (Employee) updatedObjectList.get(updatedObjectList.size() - 1);
        assertFalse(toDelete.getId().equals(lastIndexEmployee.getId()));
    }

}
