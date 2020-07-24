package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    SQLiteDatabase db;

    RadioGroup radioGroup;
    RadioButton radioButton;
    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        radioGroup=findViewById(R.id.radioGroup);
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Signup.this,
                android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.myclass));

        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                item = parent.getItemAtPosition(position).toString();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                //  TODO Auto-generated method stub
            }

        });
    }

    public void onSignUpClick(View v)
    {
        if(v.getId() == R.id.Bsignupbutton)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();


            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);
            String gender = radioButton.getText().toString();
            //     System.out.println("Gender ----"+
            //Cursor c = db.rawQuery("SELECT * from userdeteails where email="email",null");
            if(!pass1str.equals(pass2str))
            {
                Toast.makeText(Signup.this, "Password don't match!", Toast.LENGTH_SHORT).show();
            }

            else if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(name.getText().toString()) ||
                    TextUtils.isEmpty(pass1.getText().toString()) || TextUtils.isEmpty(pass2.getText().toString()) ||
                    radioGroup.getCheckedRadioButtonId()==-1)
            {
                Toast.makeText(Signup.this, "All fields must be filled!", Toast.LENGTH_SHORT).show();
            }

            else if(pass1.length()<6)
            {
                Toast.makeText(Signup.this, "Password must contain atleast 6 characters!", Toast.LENGTH_SHORT).show();

            }

            //( radioGroup.getCheckedRadioButtonId()==-1)
            else
            {
                //insert details in databse
                userdetails c = new userdetails();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setPass(pass1str);
                c.setGender(gender);
                c.setCategory(item);
                helper.insertUserdetails(c);
                startActivity(new Intent(Signup.this,login.class));
            }

        }

    }
    public void CheckButton(View v)
    {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }
}
