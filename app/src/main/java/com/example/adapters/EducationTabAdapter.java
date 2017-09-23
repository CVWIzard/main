package com.example.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.fragments.EducationTab;
import com.example.pavel.cvwizard.R;

import java.util.LinkedList;


/**
 * Created by Pavel on 8/4/2016.
 */

public class EducationTabAdapter extends RecyclerView.Adapter<EducationTabAdapter.mEducationViewHolder> {

    protected Context mContext;
    EducationTab educationTab;
    LinkedList<View> mDataset;

    public EducationTabAdapter(Context context,  EducationTab parentFragment, LinkedList<View> dataset) {
        mContext = context;
        educationTab = parentFragment;
        mDataset = dataset;
    }

    @Override
    public int getItemViewType(int position) {
        try {
            return position;
        }catch (NullPointerException e){
            return mDataset.size() -1;
        }

    }

    @Override
    public mEducationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new mEducationViewHolder(mDataset.get(viewType));


    }

    @Override
    public void onBindViewHolder(mEducationViewHolder holder, final int position) {
        holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: add cancelation logic
            educationTab.getOnNewDataSet().removeFromDataSet(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class mEducationViewHolder extends RecyclerView.ViewHolder {

        ImageView cancelBtn;

        public mEducationViewHolder(View itemView) {
            super(itemView);
            if(itemView instanceof CardView) {
                View mEducationHolder = (CardView) itemView;
                cancelBtn = (ImageView) mEducationHolder.findViewById(R.id.educaton_cancel_btn);
            }


        }


    }


}



