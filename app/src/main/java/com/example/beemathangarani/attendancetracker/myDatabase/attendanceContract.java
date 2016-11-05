package com.example.beemathangarani.attendancetracker.myDatabase;

/**
 * Created by Beema Thangarani on 17-10-2016.
 */
import android.provider.BaseColumns;
public final class attendanceContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private attendanceContract() {}

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "userTable";
        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_FIRSTNAME = "FirstName";
        public static final String COLUMN_NAME_LASTNAME = "LastName";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final String COLUMN_NAME_CONFIRMPASSWORD = "ConfirmPassword";
        public static final String COLUMN_NAME_EMAIL = "Email";
        public static final String _ID = BaseColumns._ID;



        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;
    }
}
