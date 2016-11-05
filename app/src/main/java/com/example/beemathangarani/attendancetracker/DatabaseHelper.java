package com.example.beemathangarani.attendancetracker;


import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.beemathangarani.attendancetracker.myDatabase.attendanceContract;
import com.example.beemathangarani.attendancetracker.myDatabase.attendanceContract.UserEntry;
import com.example.beemathangarani.attendancetracker.CreateAttendance;

/**
 * Created by Beema Thangarani on 17-10-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "AttendanceDb";
    public static final String UTABLE_NAME = "userTable";
    public static final String COLUMN_NAME_USERNAME = "Username";
    public static final String COLUMN_NAME_FIRSTNAME = "FirstName";
    public static final String COLUMN_NAME_LASTNAME = "LastName";
    public static final String COLUMN_NAME_PASSWORD = "Password";
    public static final String COLUMN_NAME_CONFIRMPASSWORD = "ConfirmPassword";
    public static final String COLUMN_NAME_EMAIL = "Email";
    public static final String _ID = BaseColumns._ID;

    /** VARIABLES FOR SUBJ TABLE
     *
     * @param context
     */

    public final String TABLE_SUBJ_NAME = "subjTable";
    public final String COLUMN_NAME_SUBJECT = "Subject";
    public final String COLUMN_NAME_CLASS = "Class";
    public static final String COLUMN_NAME_UNAME = "User";
    public static final String _SID = BaseColumns._ID;
    public final String COLUMN_LAST_TOOK = "LastAttendance";
    /**
     * Variables for new Attendance Table
     */

    public static String TABLE_NAME;
    public static final String COLUMN_NAME_NO = "RollNo";
    public static final String COLUMN_NAME_NAME = "Name";
    public static final String COLUMN_NAME_SID = "SID";
    public static final String _UID = BaseColumns._ID;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UTABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_USERNAME + " TEXT,"
                + COLUMN_NAME_FIRSTNAME + " TEXT,"
                + COLUMN_NAME_LASTNAME + " TEXT,"
                + COLUMN_NAME_PASSWORD + " TEXT,"
                + COLUMN_NAME_CONFIRMPASSWORD + " TEXT,"
                + COLUMN_NAME_EMAIL + " TEXT UNIQUE" + " );";
        db.execSQL(SQL_CREATE_ENTRIES);

        /** Subject class Table
         *
         */

        String SQL_CREATE_SUBJ = "CREATE TABLE " + TABLE_SUBJ_NAME + " ("
                + _SID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME_UNAME + " TEXT,"
                + COLUMN_NAME_SUBJECT + " TEXT,"
                + COLUMN_NAME_CLASS + " TEXT,"
                + COLUMN_LAST_TOOK + " TEXT"+ " );";

        db.execSQL(SQL_CREATE_SUBJ);
    }




    /**
     *  Insert the data into the User Table in Database
     * @return newRowId
     */

    public boolean insertUserData(String UserName,String FirstName,String LastName,String Password,String ConfirmPassword,String Email) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_USERNAME,UserName);
        values.put(COLUMN_NAME_FIRSTNAME,FirstName);
        values.put(COLUMN_NAME_LASTNAME, LastName);
        values.put(COLUMN_NAME_PASSWORD, Password);
        values.put(COLUMN_NAME_CONFIRMPASSWORD, ConfirmPassword);
        values.put(COLUMN_NAME_EMAIL, Email);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UTABLE_NAME, null, values);
        if(newRowId == -1){
            return false;
        }else{
            Log.v("RegisterActivity","New Row id = "+newRowId);
            return true;
        }
    }
    public String getPassword(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UTABLE_NAME,null,"Email=?",new String[]{email},null,null,null);
        if(cursor.getCount() < 1){
            cursor.close();
            return "Not Exists";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("Password"));
        cursor.close();
        return password;
    }

    /**
     *  Insert into the Subject table
     */

    public long insertSubject(String name,String subject,String cls){
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_UNAME,name);
        values.put(COLUMN_NAME_SUBJECT,subject);
        values.put(COLUMN_NAME_CLASS, cls);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_SUBJ_NAME, null, values);
        if(newRowId == -1){
            return -1;
        }else{
            Log.v("RegisterActivity","New Row id = "+newRowId);
            return newRowId;
        }
    }

    /**
     * Insert entries into attendance table
     * @param name
     * @param rollno
     * @param id
     * @return
     */
    public boolean insertName(String TABLE_NAME,String rollno,String name,String id){
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NO,rollno);
        values.put(COLUMN_NAME_NAME,name);
        values.put(COLUMN_NAME_SID,id);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_NAME,null,values);
        if(newRowId == -1){
            return false;
        }else{
            Log.v("CreateAttendance","New Row id = "+newRowId);
            return true;
        }
    }

    /**
     *
     * @param Tablename
     * /0
     * @return
     */
    public boolean updateAttendance(String rollNO,String Tablename,String Date,String isChecked){
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Date,isChecked);


        // Insert the new row, returning the primary key value of the new row
        db.update(Tablename, values, "RollNo=" + rollNO, null);
        return true;
    }

//
//    public String getLastTook(String subject){
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_SUBJ_NAME,new String[]{COLUMN_LAST_TOOK},COLUMN_NAME_SUBJECT + "=" +subject,null,null,null,null,null);
//        if(cursor.getCount() < 1){
//            cursor.close();
//            return "Not Exists";
//        }
//        cursor.moveToFirst();
//        String lastTook = cursor.getString(cursor.getColumnIndex("LastAttendance"));
//        cursor.close();
//        return lastTook;
//    }




    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(UserEntry.SQL_DELETE_ENTRIES);
        onCreate(db);

    }
    // Gets the data repository in write mode

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}