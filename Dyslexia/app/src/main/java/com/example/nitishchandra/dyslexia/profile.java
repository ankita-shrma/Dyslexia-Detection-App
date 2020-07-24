package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String disp_email = getIntent().getStringExtra("Useremail");
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        editEmail.setText(disp_email);
        editEmail.setFocusable(false);
        String fetched_email = editEmail.getText().toString();
        String[] res = new String[3];
        res = helper.getResult(fetched_email);
        EditText editName = (EditText) findViewById(R.id.editName);
        editName.setText(res[0]);
        EditText editGender = (EditText) findViewById(R.id.editGender);
        editGender.setText(res[1]);
        editGender.setFocusable(false);
        EditText editCategory = (EditText) findViewById(R.id.editCategory);
        editCategory.setText(res[2]);

    }

    public void onUpdateClick(View v) {
        if (v.getId() == R.id.btn_update)
        {
            EditText email=(EditText)findViewById(R.id.editEmail);
            String unchangedEmail=email.getText().toString();
            EditText name=(EditText)findViewById(R.id.editName);
            String updatedName=name.getText().toString();
            EditText category=(EditText)findViewById(R.id.editCategory);
            String updatedCategory=category.getText().toString();
            EditText gender=(EditText)findViewById(R.id.editGender);
            String unchangedGender=gender.getText().toString();

            if(TextUtils.isEmpty((category.getText().toString())) || TextUtils.isEmpty(name.getText().toString()))
            {
                Toast.makeText(profile.this, "Fields can't be empty!!", Toast.LENGTH_SHORT).show();
            }
            else {
                userdetails c = new userdetails();
                c.setName(updatedName);
                c.setCategory(updatedCategory);
                boolean res = helper.updateDetails(c, unchangedEmail);
                if (res == true) {
                    Toast.makeText(profile.this, "Profile is updated", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
