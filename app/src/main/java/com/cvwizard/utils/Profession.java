package com.cvwizard.utils;


import android.util.Log;

import com.cvwizard.app.R;
import com.cvwizard.storage.StorageClass;

import java.util.ArrayList;
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

        professionPersonalParameters.put("FIRSTNAME","Full Name");
        professionPersonalParameters.put("EMAIL","Email");
        professionPersonalParameters.put("CONTACTNUMBER","Contact Number");
        professionPersonalParameters.put("DATEOFBIRTH","Date of birth");
        professionPersonalParameters.put("ADDRESS","Homecity");

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
