package com.cvwizard.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cvwizard.Interfaces.EditTextInterfaces;
import com.cvwizard.app.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pavel_Anna on 10/29/2017.
 */

public class ViewCreator {

    private  LayoutInflater mLayoutInflater;
    private  Context mContext;

    public ViewCreator(LayoutInflater layoutInflater, Context context) {

        this.mLayoutInflater = layoutInflater;
        this.mContext = context;
    }

    public CardView createDynamicViewFromJson(JSONObject json, final EditTextInterfaces editTextInterfaces){
        CardView genericCardView = (CardView) mLayoutInflater.inflate(R.layout.generic_cardview,null);
        try {
            ((AppCompatTextView) genericCardView.findViewById(R.id.topic_textview)).setText(json.getString("title"));
            JSONArray jsonArray = json.getJSONArray("edittexts");
            for(int i = 0; i < jsonArray.length(); i++) {
                LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(R.layout.activity_details_child_texts, null);
                final TextInputLayout textInputLayout = (TextInputLayout) linearLayout.findViewById(R.id.detail_edittext_layout);
                final TextInputEditText editText = (TextInputEditText) linearLayout.findViewById(R.id.detail_edittext);
                Typeface typeface = Typeface.createFromAsset(mContext.getAssets(),"font/Quicksand-Medium.ttf");
                textInputLayout.setTypeface(typeface);
                editText.setHighlightColor(ContextCompat.getColor(mContext,R.color.white));
                textInputLayout.setHint(jsonArray.getString(i));
                ((LinearLayout) genericCardView.findViewById(R.id.generic_content_contaner)).addView(linearLayout);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            editTextInterfaces.focusChange(((TextInputEditText) v));
                        }
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genericCardView;
    }
}
