package me.lukasz.database.entities;

import java.sql.Timestamp;

public class EmpLogin
{

    private int id;
    private String username;
    private String password;
    private Timestamp timestamp;

    public EmpLogin(int id, String username, String password, Timestamp timestamp)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.timestamp = timestamp;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp)
    {
        this.timestamp = timestamp;
    }
}
