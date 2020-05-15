package me.lukasz.utils;

public abstract class CliUtil
{

    public void printText(String text)
    {
        System.out.println(text);
    }

    public void printTextPrefix(String text)
    {
        System.out.println(Msg.PREFIX + text);
    }

}
