package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class generalTestLevel1 extends AppCompatActivity {

    public int section=1,score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_test_level1);
    }

    public void onGenerallevel1(View v)
    {
        if(v.getId()==R.id.phonol1)
        {
            Intent i=new Intent(this,GeneralTest.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }
    }
}
