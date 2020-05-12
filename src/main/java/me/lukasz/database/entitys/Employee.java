package me.lukasz.database.entitys;

public class Employee
{

    private int id;
    private String fname;
    private String lname;
    private Enum permissions;
    private String work_sector;
    private String email;

    public Employee(int id, String fname, String lname, Enum permissions, String work_sector, String email)
    {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.permissions = permissions;
        this.work_sector = work_sector;
        this.email = email;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    public Enum getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Enum permissions)
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
}
