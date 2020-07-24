package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class GeneralTest extends AppCompatActivity {

    private static final String TAG = "1" ;
    DatabaseHelper myDb;
    TextView question,visualUserans,previous;
    Button option1,option2,option3,userans;
    ArrayList questions;
    Cursor res;
    TextView visualOption1,visualOption2,visualOption3;
    String checkans;
    // Button option2;
    TextToSpeech textToSpeech;
    String answer,que;
    String co,o1,o2;
    int lscore,counter=1,flag=0;
    int psection;
    int myColor;
    int tSec = 0;

    public class ques
    {
        public String quest,cor,op1,op2,tSection;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      Bundle extras=getIntent().getExtras();
        psection=extras.getInt("Section");
        lscore=extras.getInt("Score");
        myDb=new DatabaseHelper(this);
        questions=new ArrayList();
        Log.d(TAG,"psection="+psection);
        if(psection==1)
        {
            setContentView(R.layout.activity_phonologicaltest);
            res = myDb.getAllPhonoData();
        }
        else
        //else if (psection==2)
        {

            setContentView(R.layout.activity_visual_test);
            res = myDb.getAllVisualData();



        }
//        else if(psection==3)
//        {
//            res = myDb.getPhonoLevel3();
//        }
//        else
//        {
//            res = myDb.getPhonoLevel4();
//        }
        //AddData();
        while (res.moveToNext()) {
            ques q = new ques();
            q.quest=res.getString(1);
            q.cor = res.getString(2);
            q.op1 = res.getString(3);
            q.op2 = res.getString(4);
            q.tSection= res.getString(5);
            questions.add(q);
        }

        if (psection==1)
        {
            DisplayPhono(counter,questions.get(counter-1));
        }
        else
        {
            DisplayVisual(counter,questions.get(counter-1));
        }


    }
    /* public void AddData()
     {
         myDb.insertData("A","E","K","EASY");
         myDb.insertData("B","P","T","EASY");
         myDb.insertData("C","D","S","EASY");
         myDb.insertData("D","T","E","EASY");
     }
     */
    public void DisplayPhono(int c,Object o)
    {   /*simpleChronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
        simpleChronometer.start();*/
        // Cursor res=myDb.getAllData();
        option1=(Button) findViewById(R.id.Option1);
        option2=(Button) findViewById(R.id.Option2);
        option3=(Button) findViewById(R.id.Option3);
        question=(TextView) findViewById(R.id.Question);

        ArrayList<String>options=new ArrayList<String>();
        ques q1=(ques)o;
        options.add(q1.cor);
        options.add(q1.op1);
        options.add(q1.op2);
        tSec = Integer.parseInt( q1.tSection);
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
                    if(tSec!=4){

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

    public void DisplayVisual(int c,Object o)
    {
        ArrayList<String> options=new ArrayList<String>();
        ques qv=(ques)o;
        options.add(qv.cor);
        options.add(qv.op1);
        options.add(qv.op2);
        que=qv.quest;
        tSec = Integer.parseInt( qv.tSection);
        System.out.println("tSec "+tSec);
        answer=options.get(0).toString();
        Collections.shuffle(options);

        if (tSec==2)
        {
            setContentView(R.layout.activity_visual_level2);


            System.out.println("Database Answer: "+answer);
            visualOption1=(TextView) findViewById(R.id.option1);
            visualOption2=(TextView) findViewById(R.id.option2);
            visualOption3=(TextView) findViewById(R.id.option3);
            question=(TextView) findViewById(R.id.question);
            question.setText(que);
            visualOption1.setText(options.get(0));
            visualOption2.setText(options.get(1));
            visualOption3.setText(options.get(2));
        }
        else
        {
            setContentView(R.layout.activity_visual_test);

            option1=(Button) findViewById(R.id.option1);
            option2=(Button) findViewById(R.id.option2);
            option3=(Button) findViewById(R.id.option3);
            question=(TextView) findViewById(R.id.question);
            question.setText(que);
            try {
                System.out.println("DBColor answer = " + answer);
                myColor = Color.parseColor(answer);
                System.out.println("mycolor -----------  " + myColor);
                question.setTextColor(myColor);
            }catch (IllegalArgumentException e)
            {
                myColor = Color.parseColor(answer);
                System.out.println("Exception occured"+e+" with color value: "+myColor);
            }
            option1.setText(options.get(0));
            option2.setText(options.get(1));
            option3.setText(options.get(2));
        }

    }

    public void imageClick(View v)
    {
        if(flag==0) {
            visualUserans = (TextView) v;
            visualUserans.setTextColor(Color.BLACK);
            previous=visualUserans;
            flag=1;
        }
        else
        {
            previous.setTextColor(Color.WHITE);
            visualUserans= (TextView) v;
            visualUserans.setTextColor(Color.BLACK);
            previous=visualUserans;
        }
        }
    public void onSubmit(View v)
    {

        textToSpeech.speak("",TextToSpeech.QUEUE_FLUSH,null);
        counter++;
        userans=(Button)findViewById(v.getId());
        String checkans=userans.getText().toString();
        if(checkans.equals(answer))
        {
            lscore++;
        }

        if(counter<6)
        {
                DisplayPhono(counter,questions.get(counter-1));

        }
        else{
                System.out.println("Phono score:"+lscore);
                Intent i = new Intent(this, general_test_level2.class);
                i.putExtra("Score", lscore);
                startActivity(i);

        }
    }

    public void onVisualSubmit(View v) {

        counter++;
        if (tSec ==2)
        {

            checkans = visualUserans.getText().toString();
           // System.out.println("visua sec 2 answer: "+checkans);
        }
        else
        {
            userans=(Button)findViewById(v.getId());
            checkans=userans.getText().toString();
            System.out.println("visua sec 1 answer: "+checkans);
        }

        if (checkans.equals(answer)) {
            lscore++;
            System.out.println("score increment: "+lscore);
        }

        if (counter < 6) {

            if (tSec == 2) {
                visualUserans.setTextColor(Color.WHITE);
                DisplayVisual(counter, questions.get(counter - 1));
            } else {
                DisplayVisual(counter, questions.get(counter - 1));
            }

        }
        else
            {
                System.out.println("At visual:"+lscore);
                Intent i = new Intent(this, general_test_result.class);
                i.putExtra("Score", lscore);
                startActivity(i);

        }
    }
}
