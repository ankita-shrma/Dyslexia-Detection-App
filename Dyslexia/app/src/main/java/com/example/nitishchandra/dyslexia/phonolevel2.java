package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class phonolevel2 extends AppCompatActivity {
    public int section=2,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonolevel2);
        Bundle extras=getIntent().getExtras();
        score=extras.getInt("Score");
        System.out.println("1-----"+score);
    }
    public void onPhonolevel2(View v)
    {
        if(v.getId()==R.id.phonol2)
        {
            Intent i=new Intent(this,Phonologicaltest.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }
    }
}
