package com.example.nitishchandra.dyslexia;

//import android.appcompat.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Phonologicaltest extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView question;
    Button option1,option2,option3,userans;
    ArrayList questions;
    Cursor res;
    // Button option2;
    TextToSpeech textToSpeech;
    String answer,que;
    String co,o1,o2;
    int lscore,counter=1;
    int psection;
    // long pauseOffset;
    //Chronometer simpleChronometer;
    public class ques
    {
        public String quest,cor,op1,op2;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonologicaltest);
        Bundle extras=getIntent().getExtras();
        psection=extras.getInt("Section");
        lscore=extras.getInt("Score");
        // simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        myDb=new DatabaseHelper(this);
        questions=new ArrayList();
        if(psection==1)
        {
            res = myDb.getPhonoLevel1();
        }
        else if (psection==2)
        {
            res = myDb.getPhonoLevel2();
        }
        else if(psection==3)
        {
            res = myDb.getPhonoLevel3();
        }
        else
        {
            res = myDb.getPhonoLevel4();
        }
        //AddData();
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
    /* public void AddData()
     {
         myDb.insertData("A","E","K","EASY");
         myDb.insertData("B","P","T","EASY");
         myDb.insertData("C","D","S","EASY");
         myDb.insertData("D","T","E","EASY");
     }
     */
    public void Display(int c,Object o)
    {   /*simpleChronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
        simpleChronometer.start();*/
        // Cursor res=myDb.getAllData();
        option1=(Button) findViewById(R.id.Option1);
        option2=(Button) findViewById(R.id.Option2);
        option3=(Button) findViewById(R.id.Option3);
        question=(TextView) findViewById(R.id.Question);

        ArrayList<String>options=new ArrayList<String>();
        // res.moveToFirst();
       /* int i=0;
        while(!res.isAfterLast())
        {
            array[i]=res.getString(1);
            i++;
            res.moveToNext();
        }*/
        ques q1=(ques)o;
        options.add(q1.cor);
        options.add(q1.op1);
        options.add(q1.op2);
        que=q1.quest;
        answer=options.get(0).toString();
        Collections.shuffle(options);
        question.setText("Question"+c);
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        /*co = option1.getText().toString();
        o1=option2.getText().toString();
        o2=option3.getText().toString();*/

        textToSpeech=new TextToSpeech(getApplicationContext(),new TextToSpeech.OnInitListener(){

            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    int lang = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (lang == TextToSpeech.LANG_MISSING_DATA || lang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "THIS LANGUAGE IS NOT SUPPORTED");
                    }
                    if(psection!=4){

                        textToSpeech.playSilence(1250,TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.speak(que,TextToSpeech.QUEUE_FLUSH,null);
                    }
                    else
                    {

                        //   textToSpeech.speak(que+"options are"+option1.getText().toString()+option2.getText().toString()+option3.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                        textToSpeech.playSilence(1250,TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.speak(que,TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.playSilence(1250,TextToSpeech.QUEUE_ADD,null);
                        //  textToSpeech.speak("Options are",TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.playSilence(550,TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.speak(option1.getText().toString(),TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.playSilence(1250,TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.speak(option2.getText().toString(),TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.playSilence(1250,TextToSpeech.QUEUE_ADD,null);
                        textToSpeech.speak(option3.getText().toString(),TextToSpeech.QUEUE_ADD,null);
                    }
                }
            }
        } );
        // textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH,null);





    }
    public void onSubmit(View v)
    {   textToSpeech.speak("",TextToSpeech.QUEUE_FLUSH,null);
        /*simpleChronometer.stop();
        long t=SystemClock.elapsedRealtime()-simpleChronometer.getBase();
        System.out.println("Time taken:"+t);
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;*/
        counter++;
        userans=(Button)findViewById(v.getId());
        String checkans=userans.getText().toString();
        if(checkans.equals(answer))
        {
            lscore++;
        }
       /* if((psection==4)&&((checkans.substring(checkans.length()-3).equals(que.substring(que.length()-3)))||(checkans.substring(checkans.length()-2).equals(que.substring(que.length()-2))))){
            lscore++;
        }*/
        if(counter<6) {
            Display(counter,questions.get(counter-1));


        }
        else{
            if(psection==1)
            {
                Intent i = new Intent(this, phonolevel2.class);
                i.putExtra("Score", lscore);
                startActivity(i);
            }
            else if(psection==2)
            {
                Intent i = new Intent(this, phonolevel3.class);
                i.putExtra("Score", lscore);
                startActivity(i);
            }
            else if(psection==3)
            {
                Intent i = new Intent(this, phonolevel4.class);
                i.putExtra("Score", lscore);
                startActivity(i);
            }
            else {
                Intent i = new Intent(this, PhonologicalResult.class);
                i.putExtra("Score", lscore);
                startActivity(i);
            }
        }
    }
}
