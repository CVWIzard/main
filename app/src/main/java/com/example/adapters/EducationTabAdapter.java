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


/**
 * Created by Pavel on 8/4/2016.
 */

public class EducationTabAdapter extends RecyclerView.Adapter<EducationTabAdapter.mEducationViewHolder> {

    protected Context mContext;


    public EducationTabAdapter(Context context, FragmentManager childFragmentManager,Fragment parentFragment) {
        mContext = context;


    }

    @Override
    public int getItemViewType(int position) {
        try {
            return position;
        }catch (NullPointerException e){
            return EducationTab.mEducationDataSet.size() -1;
        }

    }

    @Override
    public mEducationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Spinner mStartDate, mEndDate;
        View mMilitaryLayout = LayoutInflater.from(mContext).inflate(R.layout.education_military_service_layout, parent, false);
        ArrayAdapter<CharSequence> spinnerAdapterStart = ArrayAdapter.createFromResource(mContext, R.array.yearsOfServiceStart, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> spinnerAdapterEnd = ArrayAdapter.createFromResource(mContext, R.array.yearsOfServiceEnd, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapterStart.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStartDate = (Spinner) mMilitaryLayout.findViewById(R.id.start_of_service);
        mEndDate = (Spinner) mMilitaryLayout.findViewById(R.id.end_of_service);
        mStartDate.setAdapter(spinnerAdapterStart);
        mEndDate.setAdapter(spinnerAdapterEnd);

        View mAcademicLayout = LayoutInflater.from(mContext).inflate(R.layout.education_academy_info, parent, false);
        Spinner aStartDate = (Spinner) mAcademicLayout.findViewById(R.id.start_of_service);
        Spinner aEndDate = (Spinner) mAcademicLayout.findViewById(R.id.end_of_service);
        aStartDate.setAdapter(spinnerAdapterStart);
        aEndDate.setAdapter(spinnerAdapterEnd);



        switch (viewType) {
            case 0:
                return new mEducationViewHolder(mMilitaryLayout);
            case 1:
                return new mEducationViewHolder(mAcademicLayout);
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(mEducationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return EducationTab.mEducationDataSet.size();
    }


    public class mEducationViewHolder extends RecyclerView.ViewHolder {

        public mEducationViewHolder(View itemView) {
            super(itemView);
            if(itemView instanceof CardView) {
                View mEducationHolder = (CardView) itemView;
            }

        }


    }


}



