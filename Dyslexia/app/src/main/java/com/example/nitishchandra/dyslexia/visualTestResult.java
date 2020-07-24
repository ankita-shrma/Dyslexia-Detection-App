package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class visualTestResult extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView V_score,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_test_result);
        message=(TextView)findViewById(R.id.msg);
        V_score=(TextView)findViewById(R.id.v_score);
        Bundle extras=getIntent().getExtras();
        int vscore=extras.getInt("Score");
        System.out.println("visual score ----"+vscore);

        V_score.setText(Integer.toString(vscore));
        if(vscore<=3)
            message.setText("You may have Visual dyslexia");
        else
            message.setText("All ok");
        myDb=new DatabaseHelper(this);
        myDb.insertData("john@gmail.com","Visual Dyslexia Test",vscore);
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
