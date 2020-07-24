package com.example.nitishchandra.dyslexia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;


public class visualLevel1 extends AppCompatActivity {
    public int section=1,score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_level1);

    }
    public void onVisualLevel1(View v)
    {
        if(v.getId()==R.id.visuall1)
        {
            Intent i=new Intent(this,visualTest.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }

    }
}
