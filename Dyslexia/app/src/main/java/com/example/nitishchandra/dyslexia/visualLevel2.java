package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class visualLevel2 extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView question,userans,previous;
    TextView option1,option2,option3;
    ArrayList questions;
    Cursor res;
    String checkans;
    String answer,que;
    String co,o1,o2;
    int lscore,counter=1,flag=0;
    int vsection;
    public class ques
    {
        public String quest,cor,op1,op2;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_level2);
        Bundle extras=getIntent().getExtras();
        vsection=extras.getInt("Section");
        lscore=extras.getInt("Score");
        myDb=new DatabaseHelper(this);
        questions=new ArrayList();
        res = myDb.getVisualLevel2();
        while (res.moveToNext()) {
            ques q = new ques();
            q.quest=res.getString(1);
            q.cor = res.getString(2);
            q.op1 = res.getString(3);
            q.op2 = res.getString(4);
            questions.add(q);
        }
        Display(counter,questions.get(counter-1));
    }
    public void imageClick(View v)
    {
        if(flag==0) {
            userans = (TextView) v;
            userans.setTextColor(Color.BLACK);
            previous=userans;
            flag=1;
        }
        else
        {
            previous.setTextColor(Color.WHITE);
            userans = (TextView) v;
            userans.setTextColor(Color.BLACK);
            previous=userans;
        }
        //userans.setLayoutParams(new ViewGroup.LayoutParams(250,250));
        // userans.setWidth(250);
        // userans.setHeight(250);
        //RelativeLayout.LayoutParams params=new  RelativeLayout.LayoutParams(350,350);
        //userans.setLayoutParams(params);
       /* image.setLayoutParams(params);
        image.setAdjustViewBounds(true);*/
        //image.setPadding(1,1,1,1);
        // image.setBackgroundColor(Color.rgb(100,100,50));

    }
    public void Display(int c,Object o)
    {
        option1=(TextView) findViewById(R.id.option1);
        option2=(TextView) findViewById(R.id.option2);
        option3=(TextView) findViewById(R.id.option3);
        question=(TextView) findViewById(R.id.question);
        ArrayList<String>options=new ArrayList<String>();
        ques q1=(ques)o;
        options.add(q1.cor);
        options.add(q1.op1);
        options.add(q1.op2);
        que=q1.quest;
        answer=options.get(0).toString();
        Collections.shuffle(options);
        question.setText(que);
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
    }
    public void onVisualSubmit(View v)
    {

        counter++;

        checkans=userans.getText().toString();
        if(checkans.equals(answer))
        {
            lscore++;
        }
        if(counter<6) {
            userans.setTextColor(Color.WHITE);
            Display(counter,questions.get(counter-1));


        }
        else{
            Intent i = new Intent(this, visualTestResult.class);
            i.putExtra("Score", lscore);
            startActivity(i);
        }
    }


}