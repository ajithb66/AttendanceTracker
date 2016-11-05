package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("SESSION_ID");
    }
    public void goToAttendanceHome(View view){
        Intent intent = new Intent(this,AttendanceHome.class);
        intent.putExtra("SESSION_ID",name);
        startActivity(intent);
    }
    public void goToLogHome(View view){
        Intent intent = new Intent(this,LoginHome.class);
        intent.putExtra("SESSION_ID",name);
        startActivity(intent);
    }
}
