package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class dysgraphia_result extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_score);
        TextView sch=(TextView)findViewById(R.id.textView);
        Bundle extras=getIntent().getExtras();
        int pscore=extras.getInt("Score");
        sch.setText(Integer.toString(pscore));
    }

    public void goBack(View v)
    {
        if(v.getId()==R.id.buttonok) {
            startActivity(new Intent(dysgraphia_result.this, login.class));
        }
    }

}