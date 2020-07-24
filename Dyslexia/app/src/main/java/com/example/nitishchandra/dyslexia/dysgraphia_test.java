package com.example.nitishchandra.dyslexia;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.ArrayList;

public class dysgraphia_test extends AppCompatActivity {
    DatabaseHelper myDb;
    ArrayList questions;
    Cursor res;
    int counter=1,score=0,size;
    String que,result;
    private PaintView paintView;
    // TextView Show;
    public Bitmap bitmap;

    public class ques{
        String quest;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dysgraphiatest);
        paintView = (PaintView) findViewById(R.id.paintView);
        //Show=(TextView) findViewById(R.id.show);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        myDb=new DatabaseHelper(this);
        questions=new ArrayList();
        res=myDb.getQuestion();
        while (res.moveToNext()) {
            ques q = new ques();
            q.quest=res.getString(1);
            questions.add(q);
        }
        size=questions.size();
        Display(questions.get(counter-1));

    }

    public void Display(Object o)
    {
        TextView disp_question=(TextView) findViewById(R.id.dysgques);
        ques q1=(ques)o;
        que=q1.quest;
        disp_question.setText(que);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.dysgraphia, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                return true;
            case R.id.emboss:
                paintView.emboss();
                return true;
            case R.id.blur:
                paintView.blur();
                return true;
            case R.id.clear:
                paintView.clear();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void onSaveImg(View v) {
        counter++;
        if (v.getId() == R.id.saveimg){
            paintView.setDrawingCacheEnabled(true);
            paintView.buildDrawingCache();
            bitmap = paintView.getDrawingCache();
            TextRecognizer textRecognizer=new TextRecognizer.Builder(getApplicationContext()).build();
            if(!textRecognizer.isOperational())
            {
                Toast.makeText(getApplicationContext(),"Could not get the Text", Toast.LENGTH_SHORT).show();
            }
            else{
                Frame frame=new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items=textRecognizer.detect(frame);
                StringBuilder sb=new StringBuilder();
                for(int i=0;i<items.size();++i)
                {
                    TextBlock myItem=items.valueAt(i);
                    sb.append(myItem.getValue());
                    sb.append("\n");
                }
                TextView show=(TextView)findViewById(R.id.show);
                result=sb.toString().toLowerCase();
                show.setText(result);
                paintView.setDrawingCacheEnabled(false);
            }

            if(result.equals(que.toLowerCase()))
            {
                score++;
            }

            if(counter<=size)
            {
                Display(questions.get(counter-1));
            }

            else{
                /*Intent i = new Intent(this, dysgraphia_result.class);
                i.putExtra("Score", score);
                startActivity(i);*/
                Toast.makeText(getApplicationContext(),"score is"+score, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
