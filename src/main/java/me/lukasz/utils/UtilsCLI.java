package me.lukasz.utils;

public abstract class UtilsCLI
{

    public void printText(String text)
    {
        System.out.println(text);
    }

    public void printTextPrefix(String text)
    {
        System.out.println(MsgUtil.PREFIX + text);
    }

}
