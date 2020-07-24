package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;

public class DyscalculiaTest extends AppCompatActivity {

    DatabaseHelper myDb;
    ImageView imgView;
    Button opt1,opt2,opt3,userans;
    String answer;
    byte[] que;
    Cursor res;
    Drawable resource;
    ArrayList questions;
    int lscore,counter=1;
    Bitmap bmp;
    int psection;

    public class ques
    {
        public String cor,op1,op2;
        public byte[] quest;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyscalculia_test);
        Bundle extras=getIntent().getExtras();
        psection=extras.getInt("Section");
        lscore=extras.getInt("Score");

        myDb=new DatabaseHelper(this);
        questions=new ArrayList();
        if(psection==1)
        {
            res = myDb.getDyscalLevel1();
        }
        if (psection==2)
        {
            res = myDb.getDyscalLevel2();
        }

        while (res.moveToNext()) {
            ques q = new ques();
            q.quest=res.getBlob(1);
            q.cor = res.getString(2);
            q.op1 = res.getString(3);
            q.op2 = res.getString(4);
            questions.add(q);
        }
        Display(counter,questions.get(counter-1));

    }
   /* public void AddValues()
    {

        myDb.insertValues("obj1","1","2","3","4","EASY");
        myDb.insertValues("obj3","3","4","1","2","EASY");
        myDb.insertValues("obj2","2","3","4","1","EASY");
        myDb.insertValues("obj4","4","1","2","3","EASY");
    }*/
    public void Display(int counter, Object o)
    {

        opt1=(Button) findViewById(R.id.opt1);
        opt2=(Button) findViewById(R.id.opt2);
        opt3=(Button) findViewById(R.id.opt3);
        imgView=(ImageView) findViewById(R.id.imgDyscal);

        ArrayList<String> options=new ArrayList<String>();
        ques q1=(ques)o;
        options.add(q1.cor);
        options.add(q1.op1);
        options.add(q1.op2);
        que=q1.quest;
        /*  System.out.println("Data----");
        for (String data : array) {
            System.out.println(array);
        }
*/
        answer=options.get(0).toString();
      //  System.out.println("Answer--------"+answer);

        Collections.shuffle(options);
        imgView=(ImageView)findViewById(R.id.imgDyscal);

        System.out.println("Question--------"+que);
        bmp= BitmapFactory.decodeByteArray(que,0,que.length);
        imgView.setImageBitmap(bmp);
        opt1.setText(options.get(0));
        opt2.setText(options.get(1));
        opt3.setText(options.get(2));

    }
    public void onSubmit(View v)
    {   counter++;
        userans=(Button)findViewById(v.getId());
        String checkans=userans.getText().toString();
        if(checkans.equals(answer))
        {
            lscore++;
        }
        if(counter<6) {
            Display(counter, questions.get(counter - 1));

        }
        else{
            if(psection==1)
            {
                Intent i = new Intent(this, dyscal2.class);
                i.putExtra("Score", lscore);
                startActivity(i);
            }
            else {
                Intent i = new Intent(this, DyscalculiaResult.class);
                i.putExtra("Score", lscore);
                startActivity(i);
            }
        }
    }
}
