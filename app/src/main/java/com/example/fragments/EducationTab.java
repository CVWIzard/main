package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import com.example.Interfaces.OnNewDataSet;
import com.example.adapters.EducationTabAdapter;
import com.example.pavel.cvwizard.R;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Pavel on 7/26/2016.
 */

public class EducationTab extends Fragment  {


 public  LinkedList<View> mEducationDataSet;
    public  EducationTabAdapter educationDetailsAdapter;
    private LayoutInflater mLayoutInflater;
    public RecyclerView recyclerView;
    OnNewDataSet onNewDataSet;


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
            public void removeFromDataSet(int position) {
                EducationTab.this.removeFromDataSet(position);
            }
        };

    }

    public OnNewDataSet getOnNewDataSet() {
        return onNewDataSet;
    }

    public void addToDataSet(int viewRecource){
        mEducationDataSet.add(mLayoutInflater.inflate(viewRecource,null));
        educationDetailsAdapter.notifyDataSetChanged();
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
