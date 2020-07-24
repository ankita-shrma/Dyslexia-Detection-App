package com.example.nitishchandra.dyslexia.Model;

public class usertests {
    String testname,date;
    int score;
    public usertests(String tname,int s,String d)
    {
        this.testname=tname;
        this.score=s;
        this.date=d;
    }
    public usertests(){}
    public int getScore()
    {
        return score;
    }
    public String getTestname()
    {
        return testname;
    }
    public String getDate()
    {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }
}
