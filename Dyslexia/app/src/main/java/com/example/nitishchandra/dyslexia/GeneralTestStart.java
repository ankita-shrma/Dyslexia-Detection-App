package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GeneralTestStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_test_start);
    }

    public void onGeneralTestStart(View v)
    {
        if(v.getId()==R.id.button1)
        {
            startActivity(new Intent(this,generalTestLevel1.class));
        }
    }
}
