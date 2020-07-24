package com.example.nitishchandra.dyslexia;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class visualTest extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView question;
    Button option1,option2,option3,userans;
    ArrayList questions;
    Cursor res;
    String answer,que;
    String co,o1,o2;
    int lscore,counter=1;
    int vsection,myColor;
    public class ques
    {
        public String quest,cor,op1,op2;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_test);

        Bundle extras=getIntent().getExtras();
        vsection=extras.getInt("Section");
        lscore=extras.getInt("Score");
        myDb=new DatabaseHelper(this);
        questions=new ArrayList();
        res = myDb.getVisualLevel1();
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

    public void Display(int c,Object o)
    {
        option1=(Button) findViewById(R.id.option1);
        option2=(Button) findViewById(R.id.option2);
        option3=(Button) findViewById(R.id.option3);
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
        myColor = Color.parseColor(answer);
        question.setTextColor(myColor);
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
    }
    public void onVisualSubmit(View v)
    {
        counter++;
        userans=(Button)findViewById(v.getId());
        String checkans=userans.getText().toString();
        if(checkans.equals(answer))
        {
            lscore++;
        }
        if(counter<6) {
            Display(counter,questions.get(counter-1));


        }
        else{
            Intent i = new Intent(this, visualLevel2Start.class);
            i.putExtra("Score", lscore);
            startActivity(i);
        }
    }

}
