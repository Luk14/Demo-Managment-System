package me.lukasz.utils;

import java.util.Scanner;

public class Scan
{

    public static Scanner scanner = new Scanner(System.in);

    public static String getString()
    {
        String s = "";
        s = scanner.nextLine();
        return s;
    }

    public static int getInt()
    {
        int i = 0;
        try
        {
            i = Integer.parseInt(scanner.nextLine());
        } catch (Exception e)
        {
            System.out.println(MsgUtil.PREFIX + " Wrong Value, required Int!");
        }
        return i;
    }

    public static double getDouble()
    {
        double i = 0;
        try
        {
            i = Double.parseDouble(scanner.nextLine());
        } catch (Exception e)
        {
            System.out.println(MsgUtil.PREFIX + " Wrong Value, required Double!");
        }
        return i;
    }
}
