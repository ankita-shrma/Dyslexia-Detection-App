package com.example.nitishchandra.dyslexia;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    final static String FileName="StateSave";

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue)
    {
        SharedPreferences sharedpref = ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        return sharedpref.getString(settingName,defaultValue);
    }

    public static void saveSharedSetting(Context ctx,String settingName, String settingValue)
    {
        SharedPreferences sharedpref=ctx.getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedpref.edit();
        editor.putString(settingName,settingValue);
        editor.apply();
    }
}
