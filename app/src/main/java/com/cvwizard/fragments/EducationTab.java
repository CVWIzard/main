package com.cvwizard.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;



import com.cvwizard.Interfaces.OnNewDataSet;
import com.cvwizard.adapters.EducationTabAdapter;
import com.cvwizard.app.R;
import com.cvwizard.utils.LayoutLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;


/**
 * Created by Pavel on 7/26/2016.
 */

public class EducationTab extends Fragment  {


 public  LinkedList<View> mEducationDataSet;
    public  EducationTabAdapter educationDetailsAdapter;
    private LayoutInflater mLayoutInflater;
    public RecyclerView recyclerView;
    OnNewDataSet onNewDataSet;
    LayoutLoader layoutLoader;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEducationDataSet = new LinkedList<>();
        mLayoutInflater = getLayoutInflater(savedInstanceState);
        onNewDataSet = new OnNewDataSet() {
            @Override
            public void addToDataSet(int recourceID) {
                EducationTab.this.addToDataSet(recourceID);
            }

            @Override
            public void addToDataSet(String fileName) {
                EducationTab.this.addToDataSet(fileName);
            }

            @Override
            public void removeFromDataSet(int position) {
                EducationTab.this.removeFromDataSet(position);
            }
        };

        layoutLoader = new LayoutLoader(getContext());

    }

    public OnNewDataSet getOnNewDataSet() {
        return onNewDataSet;
    }

    public void addToDataSet(int viewRecource){
        mEducationDataSet.add(mLayoutInflater.inflate(viewRecource,null));
      //  mEducationDataSet.add(createDynamicViewFromJson(layoutLoader.getJsonFile("military_layout")));
        educationDetailsAdapter.notifyDataSetChanged();
    }

    public void addToDataSet(String filename){
        //  mEducationDataSet.add(mLayoutInflater.inflate(viewRecource,null));
        mEducationDataSet.add(createDynamicViewFromJson(layoutLoader.getJsonFile(filename)));
        educationDetailsAdapter.notifyDataSetChanged();
    }

    private CardView createDynamicViewFromJson(JSONObject json){
       CardView genericCardView = (CardView) mLayoutInflater.inflate(R.layout.generic_cardview,null);
        try {
        ((AppCompatTextView) genericCardView.findViewById(R.id.topic_textview)).setText(json.getString("title"));
            JSONArray jsonArray = json.getJSONArray("edittexts");
            for(int i = 0; i < jsonArray.length(); i++) {
                LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(R.layout.activity_details_child_texts, null);
                final TextInputLayout textInputLayout = (TextInputLayout) linearLayout.findViewById(R.id.detail_edittext_layout);
                final TextInputEditText editText = (TextInputEditText) linearLayout.findViewById(R.id.detail_edittext);
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(),"font/Quicksand-Medium.ttf");
                textInputLayout.setTypeface(typeface);
                editText.setHighlightColor(ContextCompat.getColor(getContext(),R.color.white));
                textInputLayout.setHint(jsonArray.getString(i));
                ((LinearLayout) genericCardView.findViewById(R.id.generic_content_contaner)).addView(linearLayout);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return genericCardView;
    }

    public void removeFromDataSet(int position){
        mEducationDataSet.remove(position);
        educationDetailsAdapter.notifyDataSetChanged();
    }

    public  View getFromDataSet(int position){
        return mEducationDataSet.get(position);

    }


    //TODO // FIXME: 7/27/2016
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup holder = (ViewGroup) inflater.inflate(R.layout.education_main_holder,null);
         recyclerView = (RecyclerView) holder.findViewById(R.id.educaton_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mEducationDataSet.add(inflater.inflate(R.layout.education_academy_info,null));
        educationDetailsAdapter = new EducationTabAdapter(getContext(),this,mEducationDataSet);
        recyclerView.setAdapter(educationDetailsAdapter);

        return holder;
    }



}
