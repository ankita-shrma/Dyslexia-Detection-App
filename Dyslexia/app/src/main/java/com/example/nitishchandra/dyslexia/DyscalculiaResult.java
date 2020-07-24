package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DyscalculiaResult extends AppCompatActivity {
    TextView d_score,message;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyscalculia_result);
        message=(TextView)findViewById(R.id.msg);
        d_score=(TextView)findViewById(R.id.D_score);
        Bundle extras=getIntent().getExtras();
        int pscore=extras.getInt("Score");
        d_score.setText(Integer.toString(pscore));
        if(pscore<=3)
            message.setText("You may have phonological dyslexia");
        else
            message.setText("All ok");
        myDb=new DatabaseHelper(this);
        final GlobalClass globalVariable =(GlobalClass)getApplicationContext();
        final String email=globalVariable.getEmail();
        myDb.insertData(email,"Dyscalculia Dyslexia Test",pscore);
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
