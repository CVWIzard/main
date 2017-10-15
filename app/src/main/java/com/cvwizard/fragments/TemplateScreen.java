package com.cvwizard.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvwizard.adapters.RecyclerAdapter;
import com.cvwizard.app.R;

/**
 * Created by Pavel on 6/8/2016.
 */

public class TemplateScreen extends android.support.v4.app.Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.template_screen,container,false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(getContext(),2);
       // layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(getActivity().getApplicationContext(),getFragmentManager());
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}
