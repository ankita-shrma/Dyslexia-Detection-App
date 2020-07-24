package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class general_test_level2 extends AppCompatActivity {

    public int section=2,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_test_level2);
        Bundle extras=getIntent().getExtras();
        score=extras.getInt("Score");
        System.out.println("Section 1-----"+score);
    }
    public void onVisualLevel(View v)
    {
        if(v.getId()==R.id.visuall1)
        {
            Intent i=new Intent(this,GeneralTest.class);
            i.putExtra("Section",2);
            i.putExtra("Score",score);
            startActivity(i);
            finish();
        }

    }
}
