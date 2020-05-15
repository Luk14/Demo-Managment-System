package me.lukasz.database;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface MySQLExec
{
    void createRecord(Object object) throws NoSuchAlgorithmException;

    String getRecordString(int target, String field);
    ArrayList<Object> getArrayObjects();
    Object getRecordObject(int target);

    void setString(String uniqueID, String targetField, String result);
    void setInt(String uniqueID, String targetField, int result);

    void deleteRecord(int uniquePK);
}
