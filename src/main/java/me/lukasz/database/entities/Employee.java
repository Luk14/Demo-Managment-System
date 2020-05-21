package me.lukasz.database.entities;

public class Employee
{

    private String id;
    private String fname;
    private String lname;
    private userPermission permissions;
    private String work_sector;
    private String email;
    private String username;
    private String password;

    public Employee(String id, String fname, String lname, userPermission permissions, String work_sector, String email, String username, String password)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.permissions = permissions;
        this.work_sector = work_sector;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Employee(){}

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public userPermission getPermissions()
    {
        return permissions;
    }

    public void setPermissions(userPermission permissions)
    {
        this.permissions = permissions;
    }

    public String getWork_sector()
    {
        return work_sector;
    }

    public void setWork_sector(String work_sector)
    {
        this.work_sector = work_sector;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public enum userPermission
    {
        STANDARD,
        ELEVATED,
        ADMINISTRATOR,
        SUPER_USER;
    }

    @Override
    public String toString()
    {
        return "Employee: " +
                "ID: " + id + '\'' +
                ", First Name: " + fname + '\'' +
                ", Last Name: " + lname + '\'' +
                ", Permissions: " + permissions +
                ", Work Sector: " + work_sector + '\'' +
                ", Email: " + email + '\'' +
                ", Username: " + username + '\'';
    }
}
