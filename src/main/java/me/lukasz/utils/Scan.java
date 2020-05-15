package me.lukasz.utils;

import java.util.Scanner;

public class Scan
{

    public static Scanner scanner = new Scanner(System.in);

    public String getString()
    {
        String s = "";
        s = scanner.nextLine();
        return s;
    }

    public int getInt()
    {
        int i = 0;
        try
        {
            i = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e)
        {
            System.out.println(Msg.PREFIX + " Wrong Value, required Int!");
        }
        return i;
    }

}
