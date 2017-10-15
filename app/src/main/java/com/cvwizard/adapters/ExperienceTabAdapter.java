package com.cvwizard.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cvwizard.app.R;

/**
 * Created by Pavel_Anna on 8/7/2016.
 */

public class ExperienceTabAdapter extends RecyclerView.Adapter<ExperienceTabAdapter.mExperienceViewHolder> {

    Context mContext;

    public ExperienceTabAdapter(Context context) {
        this.mContext = context;
    }

    public class mExperienceViewHolder extends RecyclerView.ViewHolder {

        public mExperienceViewHolder(View itemView) {
            super(itemView);
            if(itemView instanceof CardView) {
                View mExperienceHolder = (CardView) itemView;
            }

        }


    }


    @Override
    public ExperienceTabAdapter.mExperienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Spinner mStartDate, mEndDate;
        View mWorkPlace = LayoutInflater.from(mContext).inflate(R.layout.exp_workplace_detail,parent,false);
        ArrayAdapter<CharSequence> spinnerAdapterStart = ArrayAdapter.createFromResource(mContext, R.array.yearsOfServiceStart, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> spinnerAdapterEnd = ArrayAdapter.createFromResource(mContext, R.array.yearsOfServiceEnd, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStartDate = (Spinner) mWorkPlace.findViewById(R.id.startDate);
        mEndDate = (Spinner) mWorkPlace.findViewById(R.id.endDate);
        mStartDate.setAdapter(spinnerAdapterStart);
        mEndDate.setAdapter(spinnerAdapterEnd);

        return new mExperienceViewHolder(mWorkPlace);
    }

    @Override
    public void onBindViewHolder(mExperienceViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }
}
