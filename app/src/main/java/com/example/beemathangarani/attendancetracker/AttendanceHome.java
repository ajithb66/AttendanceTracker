package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AttendanceHome extends AppCompatActivity {
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_home);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("SESSION_ID");
    }
    public void goToCreateAttendance(View view){
        Intent intent = new Intent(AttendanceHome.this,CreateAttActivity.class);
        intent.putExtra("SESSION_ID",name);
        startActivity(intent);
    }
    public void goToTakeAttendance(View view){
        Intent intent = new Intent(AttendanceHome.this,TakeAttendanceActivity.class);
        intent.putExtra("SESSION_ID",name);
        startActivity(intent);
    }
    public void goToUpdateAttendance(View view){
        Intent intent = new Intent(AttendanceHome.this,UpdateAttendanceActivity.class);
        intent.putExtra("SESSION_ID",name);
        startActivity(intent);
    }
    public void goToViewAttendance(View view){
        Intent intent = new Intent(AttendanceHome.this,ViewAttendanceActivity.class);
        intent.putExtra("SESSION_ID",name);
        startActivity(intent);
    }
}
