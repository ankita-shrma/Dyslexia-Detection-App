package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Phono_test_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phono_test_start);
    }
    public void onPhonologicalTestStart(View v)
    {
        if(v.getId()==R.id.button1)
        {
            Intent i=new Intent(this,phonolevel1.class);
            startActivity(i);
        }
    }
}
