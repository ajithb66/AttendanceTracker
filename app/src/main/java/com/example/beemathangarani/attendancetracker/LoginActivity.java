package com.example.beemathangarani.attendancetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper;
    private EditText Email;
    private EditText password;
    private Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDbHelper = new DatabaseHelper(this);
        Email = (EditText) findViewById(R.id.mail_editText);
        password = (EditText) findViewById(R.id.Password_editText);
        signIn = (Button) findViewById(R.id.SignIn_button);
        checkPassword();

    }

    public void checkPassword() {
        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = Email.getText().toString();
                String pwd = password.getText().toString();
                String storedPassword = mDbHelper.getPassword(name);
                if (pwd.equals(storedPassword)) {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this,Home.class);
                    intent.putExtra("SESSION_ID",name);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "UserName or Password incorrect", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }


    public void onNoAccount(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}
