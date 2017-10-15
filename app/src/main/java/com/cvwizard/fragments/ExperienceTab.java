package com.cvwizard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvwizard.adapters.ExperienceTabAdapter;
import com.cvwizard.app.R;

/**
 * Created by Pavel on 8/7/2016.
 */

public class ExperienceTab extends Fragment {

    public ExperienceTabAdapter experienceDetailsAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup holder = (ViewGroup) inflater.inflate(R.layout.experience_main_holder,null);
        RecyclerView mExperienceRecyclerView = new RecyclerView(getContext());
        mExperienceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        experienceDetailsAdapter = new ExperienceTabAdapter(getContext());
        mExperienceRecyclerView.setAdapter(experienceDetailsAdapter);
        holder.addView(mExperienceRecyclerView);

        return holder;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
