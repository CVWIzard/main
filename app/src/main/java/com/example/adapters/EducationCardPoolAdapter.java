package com.example.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pavel.cvwizard.DetailsActivity;
import com.example.pavel.cvwizard.R;

/**
 * Created by Pavel_Anna on 8/2/2017.
 */

public class EducationCardPoolAdapter extends RecyclerView.Adapter{

    Context mContext;

    int mImageIDs[] = {
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
            R.drawable.military_layout_pic,
    };

    public EducationCardPoolAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public class mEducationCardPoolHolder extends RecyclerView.ViewHolder{

        public mEducationCardPoolHolder(View itemView) {
            super(itemView);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new mEducationCardPoolHolder(LayoutInflater.from(mContext).inflate(R.layout.card_pool_education_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DetailsActivity.mInstance.getSupportFragmentManager().popBackStack();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageIDs.length;
    }
}
