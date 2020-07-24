package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class phonolevel3 extends AppCompatActivity {
    public int section=3,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonolevel3);
        Bundle extras=getIntent().getExtras();
        score=extras.getInt("Score");
        System.out.println("2-----"+score);
    }
    public void onPhonolevel3(View v)
    {
        if(v.getId()==R.id.phonol3)
        {
            Intent i=new Intent(this,Phonologicaltest.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }
    }
}
