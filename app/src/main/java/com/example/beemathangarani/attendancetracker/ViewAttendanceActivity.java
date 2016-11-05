package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class ViewAttendanceActivity extends AppCompatActivity {

    EditText Cls;
    EditText Subject;
    EditText Date;
    Button attview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        Cls = (EditText)findViewById(R.id.editTextclass);
        Subject = (EditText)findViewById(R.id.editTextsubj);
        Date = (EditText)findViewById(R.id.date);
        attview = (Button)findViewById(R.id.buttonview);
        goToView();
    }
    public void goToView(){

        attview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = Subject.getText().toString();
                String cls = Cls.getText().toString();
                String date = Date.getText().toString();
                if (subject.equals("")) {
                    Toast.makeText(ViewAttendanceActivity.this, "Subject Field can't be Empty", Toast.LENGTH_SHORT).show();
                } else if (cls.equals("")) {
                    Toast.makeText(ViewAttendanceActivity.this, "Class Field can't be Empty", Toast.LENGTH_SHORT).show();
                } else if (date.equals("")) {
                    Toast.makeText(ViewAttendanceActivity.this, "Date Field can't be Empty", Toast.LENGTH_SHORT).show();
                }else{
                            Intent intent = new Intent(ViewAttendanceActivity.this,ViewAtt.class);
                            intent.putExtra("SUBJECT", subject);
                            intent.putExtra("CLASS", cls);
                            intent.putExtra("DATE",date);
                            startActivity(intent);
                }
            }
        });

        }
    }
