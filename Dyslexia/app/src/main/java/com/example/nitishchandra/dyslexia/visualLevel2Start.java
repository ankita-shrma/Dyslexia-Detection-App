package com.example.nitishchandra.dyslexia;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class visualLevel2Start extends AppCompatActivity {

    public int section=2,score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_level2_start);
        Bundle extras=getIntent().getExtras();
        score=extras.getInt("Score");
        System.out.println("level1:"+score);

    }
    public void onVisualLevel2(View v)
    {
        if(v.getId()==R.id.visuall2)
        {
            Intent i=new Intent(this,visualLevel2.class);
            i.putExtra("Section",section);
            i.putExtra("Score",score);
            startActivity(i);
        }
    }
}
