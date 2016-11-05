package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class CreateAttendance extends AppCompatActivity {

    private  String name;
    private  String subject;
    private String cls;
    private String sid;
    private DatabaseHelper mDbHelper;
    private EditText RollNo;
    private EditText Name;
    private Button Done;
    private Button Add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_attendance);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("SESSION_ID");
        subject = extras.getString("SUBJECT");
        cls = extras.getString("CLASS");
        sid = extras.getString("SUBJECT_ID");
        mDbHelper = new DatabaseHelper(this);
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.TABLE_NAME = subject+cls;
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + mDbHelper.TABLE_NAME + " ("
                + mDbHelper._UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + mDbHelper.COLUMN_NAME_NO + " TEXT UNIQUE,"
                + mDbHelper.COLUMN_NAME_NAME + " TEXT,"
                + mDbHelper.COLUMN_NAME_SID + " TEXT" + " );";
        db.execSQL(SQL_CREATE_ENTRIES);

    }
    public void onAdd(View view){
        RollNo = (EditText) findViewById(R.id.RollNo);
        Name = (EditText) findViewById(R.id.NameIn);
        Add = (Button) findViewById(R.id.addButton);
        Done = (Button) findViewById(R.id.doneButton);
        Add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       String rollno = RollNo.getText().toString();
                                       String name = Name.getText().toString();
                                       boolean isInserted = mDbHelper.insertName(mDbHelper.TABLE_NAME, rollno, name, sid);
                                       if (isInserted) {
                                           Toast.makeText(CreateAttendance.this, "Added!", Toast.LENGTH_SHORT).show();
                                           ((EditText) findViewById(R.id.RollNo)).setText("");
                                           ((EditText) findViewById(R.id.NameIn)).setText("");
                                       } else {
                                           Toast.makeText(CreateAttendance.this, "Try Again", Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               }
        );
        }
    public void onDone(View view){
            Intent intent = new Intent(CreateAttendance.this,AttendanceHome.class);
            intent.putExtra("SESSION_ID",name);
            startActivity(intent);
    }
    }
