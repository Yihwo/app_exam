package com.example.a501_09.myportfolio_chungnam.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by 501-09 on 2018-04-10.
 */

public class Util {
    public static Date getNowDateTime(){
//        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        return new Date();
    }
    public static void setPlaceIndex(Context context,int index){
        SharedPreferences pref = context.getSharedPreferences("place_index",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("PLACE_INDEX",index);
        editor.commit();
    }
    public static int getPlaceIndex(Context context){
        SharedPreferences pref = context.getSharedPreferences("place_index",0);
        return pref.getInt("PLACE_INDEX",-1);
    }
    public static void setPlaceTitle(Context context, String title){
        SharedPreferences pref = context.getSharedPreferences("place_title",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PLACE_TITLE",title);
        editor.commit();
    }
    public static String getPlaceTitle(Context context){
        SharedPreferences pref = context.getSharedPreferences("place_title",0);
        return pref.getString("PLACE_TITLE","UTF-8");
    }
}
