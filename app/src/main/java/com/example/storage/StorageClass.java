package com.example.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pavel.cvwizard.WelcomeScreen;

import java.util.ArrayList;

/**
 * Created by Pavel on 6/7/2016.
 */

public class StorageClass {

 static Context context = WelcomeScreen.context;


  ArrayList<SharedPreferences> mGeneralDetail= new ArrayList<>();



   static SharedPreferences PERSONAL = context.getSharedPreferences("GENERAL",0);
    static SharedPreferences.Editor PERSONAL_EDIT = PERSONAL.edit();


  public static void saveValues(String key,String value)
  {
    PERSONAL_EDIT.putString(key,value).commit();
  }

  public static String getValues(String key){
    return PERSONAL.getString(key,"EMPTY");
  }


}
