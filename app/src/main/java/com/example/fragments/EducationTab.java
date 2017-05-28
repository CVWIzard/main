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


import com.example.adapters.EducationTabAdapter;
import com.example.pavel.cvwizard.R;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Pavel on 7/26/2016.
 */

public class EducationTab extends Fragment  {


    CardView mAcademyLayout;
 public static LinkedList<View> mEducationDataSet;
    View mMilitaryLayout,mAcademicLayout;
  public  EducationTabAdapter educationDetailsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAcademyLayout = (CardView) LayoutInflater.from(getContext()).inflate(R.layout.education_academy_info,null);
        mEducationDataSet = new LinkedList<>();
        Spinner mStartDate, mEndDate;
         mMilitaryLayout = LayoutInflater.from(getContext()).inflate(R.layout.education_military_service_layout,null);
        ArrayAdapter<CharSequence> spinnerAdapterStart = ArrayAdapter.createFromResource(getContext(), R.array.yearsOfServiceStart, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> spinnerAdapterEnd = ArrayAdapter.createFromResource(getContext(), R.array.yearsOfServiceEnd, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStartDate = (Spinner) mMilitaryLayout.findViewById(R.id.start_of_service);
        mEndDate = (Spinner) mMilitaryLayout.findViewById(R.id.end_of_service);
        mStartDate.setAdapter(spinnerAdapterStart);
        mEndDate.setAdapter(spinnerAdapterEnd);

         mAcademicLayout = LayoutInflater.from(getContext()).inflate(R.layout.education_academy_info,null);
        Spinner aStartDate = (Spinner) mAcademicLayout.findViewById(R.id.start_of_service);
        Spinner aEndDate = (Spinner) mAcademicLayout.findViewById(R.id.end_of_service);
        aStartDate.setAdapter(spinnerAdapterStart);
        aEndDate.setAdapter(spinnerAdapterEnd);
        EducationTab.addToDataSet(mMilitaryLayout);
        EducationTab.addToDataSet(mAcademicLayout);

    }

    public static void addToDataSet(View view){
        mEducationDataSet.add(view);
    }

    public static void removeFromDataSet(int position){
        mEducationDataSet.remove(position);
    }

    public static View getFromDataSet(int position){
        return mEducationDataSet.get(position);

    }


    //TODO // FIXME: 7/27/2016
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup holder = (ViewGroup) inflater.inflate(R.layout.education_main_holder,null);
        RecyclerView mEducationRecyclerView = new RecyclerView(getContext());
        mEducationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        educationDetailsAdapter = new EducationTabAdapter(getContext(),getChildFragmentManager(),this);
        mEducationRecyclerView.setAdapter(educationDetailsAdapter);
        holder.addView(mEducationRecyclerView);




        return holder;
    }


}
