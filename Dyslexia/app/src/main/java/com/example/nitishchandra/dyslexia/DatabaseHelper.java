package com.example.nitishchandra.dyslexia;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.example.nitishchandra.dyslexia.Model.usertests;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.BitSet;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =3;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "userdetails";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_CATEGORY = "category";

    public static final String TABLE_NAME2="Phonologicaltestdata";
    public static final String COL_1="ID";
    public static final String COL_2="QUESTION";
    public static final String COL_3="CORRECT";
    public static final String COL_4="OPTION1";
    public static final String COL_5="OPTION2";
    public static final String COL_6="SECTION";

    public static final String TABLE_NAME3="Dyscalculiatestdata";
    public static final String COL_ID="ID";
    public static final String COL_QUES="QUESTION";
    public static final String COL_Correct="CORRECT";
    public static final String COL_OPT2="OPTION1";
    public static final String COL_OPT3="OPTION2";
    public static final String COL_OPT4="SECTION";

    public static final String TABLE_NAME4="userstestscores";
    public static final String COLUMN_ID="scoreid";
    private static final String COLUMN_EMAIL1 = "email";
    private static final String COLUMN_TESTNAME = "testname";
    private static final  String COLUMN_SCORE = "score";
    private static final  String COLUMN_DATETIME = "date";

    public static final String TABLE_NAME5="Visualtestdata";
    public static final String COLM_1="ID";
    public static final String COLM_2="QUESTION";
    public static final String COLM_3="CORRECT";
    public static final String COLM_4="OPTION1";
    public static final String COLM_5="OPTION2";
    public static final String COLM_6="SECTION";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table " + TABLE_NAME +
            "(name text not null, email text not null primary key, pass text not null, gender text not null, category text not null );";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        //this.db = db;
        db.execSQL("create table "+ TABLE_NAME2+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,QUESTION TEXT,CORRECT TEXT,OPTION1 TEXT,OPTION2 TEXT,SECTION TEXT)");
        db.execSQL("create table "+ TABLE_NAME3+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,QUESTION BLOB,CORRECT TEXT,OPTION1 TEXT,OPTION2 TEXT,SECTION TEXT)");
        db.execSQL("create table "+ TABLE_NAME5+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,QUESTION TEXT,CORRECT TEXT,OPTION1 TEXT,OPTION2 TEXT,SECTION TEXT)");
        db.execSQL("create table "+ TABLE_NAME4+"(scoreid integer PRIMARY KEY AUTOINCREMENT,email TEXT,testname TEXT,score integer,date DATETIME DEFAULT CURRENT_TIMESTAMP,FOREIGN KEY (email) REFERENCES userdetails(email))");
        //AddData();

    }

    public void insertUserdetails(userdetails c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        /*String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        */

        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_GENDER, c.getGender());
        values.put(COLUMN_CATEGORY, c.getCategory());



        db.insert(TABLE_NAME, null, values);
        db.close();
    }

  /*  public void insertData(String correct,String option1,String option2,String level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,correct);
        contentValues.put(COL_3,option1);
        contentValues.put(COL_4,option2);
        contentValues.put(COL_5,level);
        long result=db.insert(TABLE_NAME2,null,contentValues);

    }*/

    /*public void insertValues(Blob ques, String correct, String option1, String option2, String level)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_QUES,ques);
        contentValues.put(COL_Correct,correct);
        contentValues.put(COL_OPT2,option1);
        contentValues.put(COL_OPT3,option2);
        contentValues.put(COL_OPT4,level);
        long result=db.insert(TABLE_NAME3,null,contentValues);

    }*/

    public Cursor getAllPhonoData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME2+" ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }

    public Cursor getAllVisualData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME5+" ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }

    /*public Cursor getAllValue()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME3+" ORDER BY RANDOM() LIMIT 4" ,null);
        return res;
    }*/

    public String searchPass(String email)
    {
        db = this.getReadableDatabase();
        String query = "select email, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);


                if (a.equals(email))
                {
                    b = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public Cursor getPhonoLevel1()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME2+" WHERE SECTION ='1' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }
    public Cursor getPhonoLevel2()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME2+" WHERE SECTION ='2' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }
    public Cursor getPhonoLevel3()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME2+" WHERE SECTION ='3' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }
    public Cursor getPhonoLevel4()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME2+" WHERE SECTION ='4' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }
    public Cursor getDyscalLevel1()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME3+" WHERE SECTION ='1' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }
    public Cursor getDyscalLevel2()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME3+" WHERE SECTION ='2' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }

    public Cursor getVisualLevel1()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME5+" WHERE SECTION ='1' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }
    public Cursor getVisualLevel2()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("Select DISTINCT * from "+TABLE_NAME5+" WHERE SECTION ='2' ORDER BY RANDOM() LIMIT 5" ,null);
        return res;
    }

    public void insertData(String email,String testname,int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EMAIL1,email);
        contentValues.put(COLUMN_TESTNAME ,testname);
        contentValues.put(COLUMN_SCORE, score);
        long result = db.insert(TABLE_NAME4, null, contentValues);
    }
    public ArrayList<usertests> getAllUserTests()
    {
        ArrayList<usertests> arrayList=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
      //  final GlobalClass globalVariable =(GlobalClass) getApplicationContext();
      //  final String email_n=globalVariable.getEmail();
        Cursor cursor=db.rawQuery("Select testname,score,datetime(date,'localtime')as date from "+TABLE_NAME4+" WHERE email='john@gmail.com'",null);
        while(cursor.moveToNext())
        {
            String name=cursor.getString(cursor.getColumnIndex("testname"));
            int testscore=cursor.getInt(cursor.getColumnIndex("score"));
            String testdate=cursor.getString(cursor.getColumnIndex("date"));
            usertests usertest=new usertests(name,testscore,testdate);
            arrayList.add(usertest);
        }
        return arrayList;
    }


    public String[] getResult(String temp_email)
    {
        db = this.getReadableDatabase();
        String query = "select name,email,gender,category from " + TABLE_NAME ;

        Cursor res= db.rawQuery(query, null);

        String a;
        String[] buffer=new String[3];
        if(res.moveToFirst())
        {
            do {
                a = res.getString(1);


                if (a.equals(temp_email))
                {
                    buffer[0] = res.getString(0);
                    buffer[1] = res.getString(2);
                    buffer[2] = res.getString(3);
                    break;
                }
            }while (res.moveToNext());
        }
        return buffer;
    }

    public boolean updateDetails(userdetails c, String Email)
    {
        db = this.getWritableDatabase();
        // ContentValues values = new ContentValues();
        String Upname=c.getName();
        String Upcategory=c.getCategory();
        db.execSQL("update userdetails Set name='"+Upname+"',category='"+Upcategory+"' Where email='"+Email+"'");
        return true;
    }

}
