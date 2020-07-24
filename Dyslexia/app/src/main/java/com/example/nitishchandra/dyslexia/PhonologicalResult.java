package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PhonologicalResult extends AppCompatActivity {
    TextView p_score,message;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonological_result);
        p_score=(TextView)findViewById(R.id.P_score);
        message=(TextView)findViewById(R.id.msg);
        Bundle extras=getIntent().getExtras();
        int pscore=extras.getInt("Score");
        System.out.println("level1:"+pscore);
        p_score.setText(Integer.toString(pscore));
        if(pscore<=6)
            message.setText("You may have phonological dyslexia");
        else
            message.setText("All ok");
        myDb=new DatabaseHelper(this);
        myDb.insertData("john@gmail.com","Phonological Dyslexia Test",pscore);
    }
    public void onResultGoHome(View v)
    {
        if(v.getId()==R.id.gohome)
        {
            Intent i=new Intent(this,Home.class);
            startActivity(i);
        }
    }
}
