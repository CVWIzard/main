package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.EducationCardPoolAdapter;
import com.example.pavel.cvwizard.DetailsActivity;
import com.example.pavel.cvwizard.R;

/**
 * Created by Pavel_Anna on 8/1/2017.
 */

public class EducationCardPool extends Fragment {

    RecyclerView mCardPoolRecycler;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_pool_fragment,null,false);
        mCardPoolRecycler = (RecyclerView) v.findViewById(R.id.educaton_card_pool_recycler);
        EducationCardPoolAdapter educationCardPoolAdapter = new EducationCardPoolAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2,LinearLayoutManager.VERTICAL,false);
        mCardPoolRecycler.setLayoutManager(layoutManager);
        mCardPoolRecycler.setAdapter(educationCardPoolAdapter);
        DetailsActivity.mInstance.fragmentTransaction = DetailsActivity.mInstance.getSupportFragmentManager().beginTransaction();
        return v;
    }
}
