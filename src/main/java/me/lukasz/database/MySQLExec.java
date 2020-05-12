package me.lukasz.database;

import java.util.ArrayList;

public interface MySQLExec
{
    void createRecord(String value);
    void createRecord(Object object);

    String getRecordString(String field);
    ArrayList<Object> getArrayObjects();
    Object getRecordObject();

    void setString(String record, String targetField);
    void setInt(int record, String targetField);

    void deleteRecord(String uniquePK);
    void deleteRecord(int uniquePK);
}
