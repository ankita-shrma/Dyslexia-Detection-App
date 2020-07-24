package com.example.nitishchandra.dyslexia.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.nitishchandra.dyslexia.Model.usertests;
import com.example.nitishchandra.dyslexia.R;

import java.util.ArrayList;

public class myAdapter extends BaseAdapter {
    Context context;
    ArrayList<usertests> arrayList;
    public myAdapter(Context context, ArrayList<usertests> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    public long getItemId(int position)
    {
        return position;
    }
    public Object getItem(int position)
    {
        return arrayList.get(position);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.mycustomlistview,null);
            TextView t1_name=(TextView)convertView.findViewById(R.id.tname);
            TextView t1_score=(TextView)convertView.findViewById(R.id.tscore);
            TextView t1_date=(TextView)convertView.findViewById(R.id.tdate);

            usertests usertest=arrayList.get(position);
            t1_name.setText(usertest.getTestname());
            t1_score.setText(Integer.toString(usertest.getScore()));
            t1_date.setText(usertest.getDate());
        return convertView;
    }
    public int getCount()
    {
        return this.arrayList.size();
    }

}
