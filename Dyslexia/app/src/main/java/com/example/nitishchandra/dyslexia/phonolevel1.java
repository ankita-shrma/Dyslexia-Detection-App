package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class phonolevel1 extends AppCompatActivity {
    public int section=1,score=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonolevel1);
    }
    public void onPhonolevel1(View v)
    {
        if(v.getId()==R.id.phonol1)
        {
            Intent i=new Intent(this,Phonologicaltest.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }
    }

}

