package com.cvwizard.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvwizard.Interfaces.OnNewDataSet;
import com.cvwizard.adapters.EducationCardPoolAdapter;
import com.cvwizard.DetailsActivity;
import com.cvwizard.app.R;
import com.cvwizard.utils.LayoutLoader;

/**
 * Created by Pavel_Anna on 8/1/2017.
 */

public class EducationCardPool extends Fragment {

    RecyclerView mCardPoolRecycler;
  public  OnNewDataSet onNewDataSet;
    LayoutLoader layoutLoader;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        layoutLoader = new LayoutLoader(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.card_pool_fragment,null,false);
        mCardPoolRecycler = (RecyclerView) v.findViewById(R.id.educaton_card_pool_recycler);
        EducationCardPoolAdapter educationCardPoolAdapter = new EducationCardPoolAdapter(getContext(),this,getOnNewDataSet(),layoutLoader);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mCardPoolRecycler.setLayoutManager(layoutManager);
        mCardPoolRecycler.setAdapter(educationCardPoolAdapter);
        DetailsActivity.mInstance.fragmentTransaction = DetailsActivity.mInstance.getSupportFragmentManager().beginTransaction();
        return v;
    }

    public OnNewDataSet getOnNewDataSet() {
        return onNewDataSet;
    }
}
