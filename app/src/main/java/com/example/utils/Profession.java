package com.example.utils;


import android.util.Log;

import com.example.pavel.cvwizard.R;
import com.example.storage.StorageClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

/**
 * Created by Pavel on 6/17/2016.
 */

public class Profession {

    private String name;

    private LinkedHashMap<String,String> professionPersonalParameters = new LinkedHashMap<>();
    private  LinkedHashMap<String,Integer> professionAcademicParameters = new LinkedHashMap<>();
    private LinkedHashMap<String,String> professionExpirienceParameters = new LinkedHashMap<>();

    public Profession(String professionName){
        this.name = professionName;

        professionPersonalParameters.put("FIRSTNAME","First Name");
        professionPersonalParameters.put("LASTNAME","Last Name");
        professionPersonalParameters.put("EMAIL","Email");
        professionPersonalParameters.put("CONTACTNUMBER","Contact Number");
        professionPersonalParameters.put("DATEOFBIRTH","Date of birth");
        professionPersonalParameters.put("ADDRESS","Homecity");

        professionAcademicParameters.put("militaryLayout",R.layout.education_military_service_layout);
        professionAcademicParameters.put("academyLayout",R.layout.education_academy_info);

        if(AppParameters.DEBUG) {
            Log.i(this.getClass().getSimpleName(), professionPersonalParameters.values().toString());
        }

            switch (professionName.toUpperCase()){
                case "GENERAL":
                    break;
            }

    }

    public String getName() {
        return name;
    }

    public void savePersonalValues(ArrayList<String> data){
        if(data.isEmpty()){
           return;
        }else{
            String tempString = "";
            for(String element: data){
                tempString = tempString.concat(element + ",");
            }
            StorageClass.saveValues(AppParameters.FILE_NAME,tempString);

        }
    }

    public String[] getStorageValues(){
        String TempString = StorageClass.getValues(AppParameters.FILE_NAME);
        return TempString.split(",");
    }

    public String getPersonalValue(String key){
        return  professionPersonalParameters.get(key);
    }
    public int getEducationValue (String key) {return professionAcademicParameters.get(key); }

   public String[] getPersonalValues(){
   return professionPersonalParameters.values().toArray(new String[professionPersonalParameters.size()]);
   }
}
