package com.cvwizard.utils;



import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;



/**
 * Created by Pavel on 10/15/2017.
 */

public class LayoutLoader {

    Context context;
    String TAG = getClass().getSimpleName();

    public LayoutLoader(Context context) {
        this.context = context;
    }

    public int getLayout(String filename) throws XmlPullParserException {
     InputStream istr = null;
        AssetManager assetManager = context.getAssets();
     try {
         istr = assetManager.open("static_layouts/"+filename+".xml");
     } catch (IOException e) {
         e.printStackTrace();
     }
        Log.i(TAG,istr.toString());
     XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
     factory.setNamespaceAware(true);
    XmlPullParser xrp;
     xrp = factory.newPullParser();
     xrp.setInput(istr, "UTF-8");

     int a = Integer.parseInt(xrp.getAttributeValue(null, "width"));
     return a;
 }

 //Will be brouth from server
 public JSONObject getJsonFile(String fileName) {
     String json = null;
     JSONObject obj = null;
     try {
         InputStream is = context.getAssets().open("static_layouts/" + fileName + ".json");
         int size = is.available();
         byte[] buffer = new byte[size];
         is.read(buffer);
         is.close();
         json = new String(buffer, "UTF-8");
     } catch (IOException ex) {
         ex.printStackTrace();
         return null;
     }
     try {
          obj = new JSONObject(json);

     } catch (JSONException e) {
         e.printStackTrace();
     }
     return obj;
 }
 }
