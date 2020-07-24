package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class general_test_result extends AppCompatActivity {

    TextView g_score,message;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_test_result);

        g_score=(TextView)findViewById(R.id.G_score);
        message=(TextView)findViewById(R.id.msg);
        Bundle extras=getIntent().getExtras();
        int pscore=extras.getInt("Score");
        System.out.println("level1:"+pscore);
        g_score.setText(Integer.toString(pscore));
        if(pscore<=6)
            message.setText("You may have phonological dyslexia");
        else
            message.setText("All ok");
        myDb=new DatabaseHelper(this);
        myDb.insertData("john@gmail.com","General Test",pscore);
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
