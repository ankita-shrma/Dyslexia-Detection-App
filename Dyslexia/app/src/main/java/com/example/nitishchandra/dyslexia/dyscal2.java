package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dyscal2 extends AppCompatActivity {
    public int section=2,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyscal2);
        Bundle extras=getIntent().getExtras();
        score=extras.getInt("Score");
    }
    public void ondyscal2(View v)
    {
        if(v.getId()==R.id.dyscal2)
        {
            Intent i=new Intent(this,DyscalculiaTest.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }
    }
}
