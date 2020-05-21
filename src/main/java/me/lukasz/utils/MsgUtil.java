package me.lukasz.utils;

public class MsgUtil
{

    public static final String PREFIX = "[TeslaManagement] ";
    public static final String DB_SUCCESS = PREFIX + "Database was successfully connected!";
    public static final String DB_UNSUCCESSFUL = PREFIX + "Database was unsuccessfully connected!";
    public static final String DB_ERROR = PREFIX + "Error has occurred while executing query! ";
    public static final String INVALID_OBJECT = "Invalid Object! Please ensure you are inputting the correct object!";
    public static final String SUCCESS_INPUT_OBJECT = "Your object was successfully added to the Database!";
    public static final String EXECUTED_CORRECTLY = "Your query was executed successfully!";

    public static String joinString(String[] strings)
    {
        String req = strings[0];
        String argString = "";
        argString = String.join(" ", strings);
        return argString;
    }

    public static String joinString(String[] strings, String remove)
    {
        String argString = "";
        argString = String.join(" ", strings);
        argString = argString.replace(remove, "");
        return argString;
    }

    public static boolean isNumber(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

}
