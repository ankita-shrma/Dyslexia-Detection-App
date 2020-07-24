package com.example.nitishchandra.dyslexia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.login)
        {
            EditText a = (EditText)findViewById(R.id.TFusername);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                final GlobalClass globalVariable =(GlobalClass) getApplicationContext();
                globalVariable.setEmail(str);
                Intent i = new Intent(login.this, Home.class);
                i.putExtra("Useremail", str);
                startActivity(i);
            }

            else if (TextUtils.isEmpty(a.getText().toString()) || TextUtils.isEmpty(b.getText().toString()))
            {
                Toast.makeText(login.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            }

            else
            {
                Toast.makeText(login.this, "Username and Passwords don't match!", Toast.LENGTH_SHORT).show();
            }

        }

        if(v.getId() == R.id.Bsignup)
        {
            Intent i = new Intent(login.this, Signup.class);
            startActivity(i);
        }
    }
}

