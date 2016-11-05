package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAttActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper;
    EditText Subject;
    EditText Cls;
    String name;
    Button createAttendance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_att);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("SESSION_ID");
        Subject = (EditText) findViewById(R.id.Subject);
        Cls = (EditText) findViewById(R.id.Class);
        createAttendance = (Button)findViewById(R.id.create);
        mDbHelper = new DatabaseHelper(this);
        onCreateAttendance();
    }
    public void onCreateAttendance(){
        createAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = Subject.getText().toString();
                String cls = Cls.getText().toString();
                if (subject.equals("")) {
                    Toast.makeText(CreateAttActivity.this, "Subject Field can't be Empty", Toast.LENGTH_SHORT).show();
                }else if(cls.equals("")) {
                    Toast.makeText(CreateAttActivity.this, "Class Field can't be Empty", Toast.LENGTH_SHORT).show();
                }else {
                    long isInserted = mDbHelper.insertSubject(name, subject, cls);
                    if (isInserted != -1) {
                        Toast.makeText(CreateAttActivity.this, "Created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateAttActivity.this, CreateAttendance.class);
                        intent.putExtra("SESSION_ID", name);
                        intent.putExtra("SUBJECT", subject);
                        intent.putExtra("CLASS", cls);
                        intent.putExtra("SUBJECT_ID", isInserted);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CreateAttActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        })  ;

    }



}
