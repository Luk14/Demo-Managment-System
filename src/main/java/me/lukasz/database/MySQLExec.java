package me.lukasz.database;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface MySQLExec
{
    void createRecord(Object object) throws NoSuchAlgorithmException;

    String getRecordString(String target, String field);

    ArrayList<Object> getArrayObjects();

    Object getRecordObject(String target);

    void setString(String uniqueID, String targetField, String result);

    void setInt(String uniqueID, String targetField, int result);

    void deleteRecord(String uniquePK);
}
