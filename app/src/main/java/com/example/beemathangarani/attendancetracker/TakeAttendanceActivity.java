package com.example.beemathangarani.attendancetracker;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class TakeAttendanceActivity extends AppCompatActivity {

    private EditText Subject;
    private EditText cls;
    private Button take;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);
        Subject = (EditText)findViewById(R.id.subject);
        cls = (EditText)findViewById(R.id.cls);
        take = (Button) findViewById(R.id.take);
        db = new DatabaseHelper(this);
        goToAttendance();

    }
    public void goToAttendance() {
        take.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(TakeAttendanceActivity.this, Attendance.class);
                                        String subj = Subject.getText().toString();
                                        String Cls = cls.getText().toString();
                                        String TableName = subj+Cls;
                                        TableName = TableName.replaceAll("\\s","");
                                        intent.putExtra("TABLE_NAME", TableName);
                                        Calendar calendar = Calendar.getInstance();
                                        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy  MM  dd ");
                                        String strDate = mdformat.format(calendar.getTime());
                                        strDate = strDate.replaceAll("\\s","");
                                        strDate = "DATE"+strDate;
                                        intent.putExtra("DATE", strDate);
//                                        String lastTook = db.getLastTook(subj);
//                                        if(!lastTook.equals(strDate)) {
                                            String alterTable = "ALTER TABLE " + TableName + " ADD " + strDate + " TEXT;";
                                            SQLiteDatabase mDb = db.getWritableDatabase();
                                            // Create a new map of values, where column names are the keys
//                                            ContentValues values = new ContentValues();
//                                            values.put(db.COLUMN_LAST_TOOK, strDate);
//
//
//                                            // Insert the new row, returning the primary key value of the new row
//                                            mDb.update(db.TABLE_SUBJ_NAME, values, "Subject=" + subj, null);
                                            mDb.execSQL(alterTable);

                                            startActivity(intent);
//                                        }else{
//                                            Toast.makeText(TakeAttendanceActivity.this,"Already Took Attendance!",Toast.LENGTH_SHORT).show();
//                                        }
                                    }
                                }
        );
    }

    }
