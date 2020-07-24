package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Dyscalculia_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyscalculia_start);
    }
    public void onCalculiaStart(View v)
    {
        if(v.getId()==R.id.btn_dyscal);
        Intent intent = new Intent(this,dyscal1.class);
        startActivity(intent);
        finish();
    }
}
