package me.lukasz.database;

import java.util.ArrayList;

public interface MySQLExec
{
    void createRecord(Object object);

    String getRecordString(int target, String field);
    ArrayList<Object> getArrayObjects();
    Object getRecordObject(int target);

    void setString(String target, String setField);
    void setInt(String target, int setField);

    void deleteRecord(int uniquePK);
}
