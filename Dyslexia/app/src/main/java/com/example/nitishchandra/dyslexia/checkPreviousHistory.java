package com.example.nitishchandra.dyslexia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nitishchandra.dyslexia.Adapters.myAdapter;
import com.example.nitishchandra.dyslexia.Model.usertests;

import java.util.ArrayList;

public class checkPreviousHistory extends AppCompatActivity {
    ListView l;
    DatabaseHelper myDb;
    ArrayList<usertests> arrayList;
    myAdapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_previous_history);
        l=(ListView)findViewById(R.id.listView1);
        myDb=new DatabaseHelper(this);
        arrayList=new ArrayList<>();
        loadDataInListView();
    }
    public void loadDataInListView()
    {
        arrayList=myDb.getAllUserTests();
        myadapter=new myAdapter(this,arrayList);
        l.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();


    }
}
