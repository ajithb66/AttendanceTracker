package com.example.beemathangarani.attendancetracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;



public class RegisterActivity extends AppCompatActivity {

    private DatabaseHelper mDbHelper;
    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private EditText editTextEmail;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private Button buttonSignUp;
    private String UserName;
    private String Password;
    private String ConfirmPassword;
    private String Email;
    private String FirstName;
    private String LastName;
    private String mailPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUserName = (EditText) findViewById(R.id.UserName_editText);
        editTextFirstName = (EditText) findViewById(R.id.FirstName_editText);
        editTextLastName = (EditText)findViewById(R.id.LastName_editText);
        editTextPassword = (EditText) findViewById(R.id.Password_editText);
        editTextConfirmPassword = (EditText) findViewById(R.id.Confirm_Password_editText);
        editTextEmail = (EditText) findViewById(R.id.Email_editText);
        buttonSignUp = (Button) findViewById(R.id.SignUpButton);
        mDbHelper = new DatabaseHelper(this);
        addUserData();

    }



    /**     Gets data from the register activity and validates the information and stores the data into the database
     *
     */
    public void addUserData(){

        buttonSignUp.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        UserName = editTextUserName.getText().toString();
                        Password = editTextPassword.getText().toString();
                        ConfirmPassword = editTextConfirmPassword.getText().toString();
                        Email = editTextEmail.getText().toString();
                        FirstName = editTextFirstName.getText().toString();
                        LastName = editTextLastName.getText().toString();
                        mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
                        if (UserName.equals("")) {
                            Toast.makeText(RegisterActivity.this, "Username Field can't be Empty", Toast.LENGTH_SHORT).show();
                        }else if(Password.equals("")){
                            Toast.makeText(RegisterActivity.this, "Password Field can't be Empty", Toast.LENGTH_SHORT).show();
                        }else if(FirstName.equals("")){
                            Toast.makeText(RegisterActivity.this, "FirstName Field can't be Empty", Toast.LENGTH_SHORT).show();
                        }else if(LastName.equals("")){
                            Toast.makeText(RegisterActivity.this, "LastName Field can't be Empty", Toast.LENGTH_SHORT).show();
                        }else if(ConfirmPassword.equals("")){
                            Toast.makeText(RegisterActivity.this, "Confirm Password Field can't be Empty", Toast.LENGTH_SHORT).show();
                        }else if(Email.equals("")){
                            Toast.makeText(RegisterActivity.this, "Email Field can't be Empty", Toast.LENGTH_SHORT).show();
                        }else if(!Password.equals(ConfirmPassword)){
                            Toast.makeText(RegisterActivity.this, "Password Don't Match", Toast.LENGTH_SHORT).show();
                        }else if(!Email.matches(mailPattern)){
                            Toast.makeText(RegisterActivity.this, "Enter a Valid Password", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            boolean isInserted = mDbHelper.insertUserData(UserName,FirstName,LastName,Password,ConfirmPassword,Email);
                            if (isInserted) {
                                Toast.makeText(RegisterActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }
}
