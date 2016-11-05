package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Date;
import android.widget.CheckBox;
import android.view.View;
import android.widget.Toast;

public class Attendance extends AppCompatActivity {
    DatabaseHelper mydb = new DatabaseHelper(this);
    TableLayout tl;
    private int count = 0;
    String Tablename,Date;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.attendance, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                saveAttendance();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Tablename = extras.getString("TABLE_NAME");
        Date = extras.getString("DATE");
        setContentView(R.layout.activity_attendance);
        tl = (TableLayout) findViewById(R.id.tableyout);
        TableRow rowheader = new TableRow(this);
        rowheader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowheader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"ROLLNO", "NAME", "P/A"};
        for (String c : headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowheader.addView(tv);
        }
        tl.addView(rowheader);
        //get data from sqlite database and add them to table
        SQLiteDatabase db = mydb.getReadableDatabase();
        db.beginTransaction();
        try {
            String selectquery = "SELECT RollNo,Name FROM " + Tablename + ";";
            Cursor cursor = db.rawQuery(selectquery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    //read columns data
                    String RollNo = cursor.getString(cursor.getColumnIndex("RollNo"));
                    String Name = cursor.getString(cursor.getColumnIndex("Name"));
                    TableRow row = new TableRow(this);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText = {RollNo, Name};
                    for (String text : colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);


                    }
                    String[] DateText = {RollNo};
                    int i = 0;
                    for (String text : DateText) {
                        CheckBox cb = new CheckBox(getApplicationContext());
                        cb.setGravity(Gravity.CENTER);
                        cb.setPadding(5, 5, 5, 5);
                        cb.setId(i++);
                        row.addView(cb);
                        count++;
                    }
                    tl.addView(row);


                }
            }

            db.setTransactionSuccessful();


        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
    }
    public void saveAttendance(){
        boolean updated;
        SQLiteDatabase db = mydb.getReadableDatabase();
        db.beginTransaction();
        String selectquery = "SELECT RollNo FROM " + Tablename + ";";
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                //read columns data
                String RollNo = cursor.getString(cursor.getColumnIndex("RollNo"));
                String[] colText = {RollNo};
                int j = 0;
                for (String text : colText) {
                    CheckBox check = (CheckBox) findViewById(j);
                    if (check.isChecked()) {
                        updated = mydb.updateAttendance(text, Tablename, Date, "Present");

                    } else {
                        updated = mydb.updateAttendance(text, Tablename, Date, "Absent");
                    }
                    if (updated) {
                        Toast.makeText(Attendance.this, "Saved", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(this,AttendanceHome.class);
//                        startActivity(intent);
                    } else {
                        Toast.makeText(Attendance.this, "Could Not save! Try Again!", Toast.LENGTH_SHORT).show();
                    }
                    j++;
                }
            }
        }
    }

}



