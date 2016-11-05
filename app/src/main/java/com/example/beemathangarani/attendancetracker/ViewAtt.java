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

public class ViewAtt extends AppCompatActivity {
    DatabaseHelper mydb = new DatabaseHelper(this);
    TableLayout tl;
    private int count = 0;
    String Tablename;
    String Date;
    String Cls;
    String Subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Bundle extras = getIntent().getExtras();
        Cls = extras.getString("CLASS");
        Subject = extras.getString("SUBJECT");
       Tablename = Subject+Cls;
        Date = extras.getString("DATE");
        setContentView(R.layout.activity_view_att);
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
            Date="DATE"+Date;
            String selectquery = "SELECT RollNo,Name," +Date+" FROM " + Tablename + ";";
            Cursor cursor = db.rawQuery(selectquery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    //read columns data
                    String RollNo = cursor.getString(cursor.getColumnIndex("RollNo"));
                    String Name = cursor.getString(cursor.getColumnIndex("Name"));
                    String p_A = cursor.getString(cursor.getColumnIndex(Date));
                    TableRow row = new TableRow(this);
                    row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
                    String[] colText = {RollNo, Name, p_A};
                    for (String text : colText) {
                        TextView tv = new TextView(this);
                        tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.CENTER);
                        tv.setTextSize(16);
                        tv.setPadding(5, 5, 5, 5);
                        tv.setText(text);
                        row.addView(tv);


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

}



