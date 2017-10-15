package com.cvwizard.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cvwizard.Interfaces.OnNewDataSet;
import com.cvwizard.fragments.EducationCardPool;
import com.cvwizard.DetailsActivity;
import com.cvwizard.app.R;
import com.cvwizard.utils.LayoutLoader;

import org.xmlpull.v1.XmlPullParserException;

import java.util.LinkedHashMap;

/**
 * Created by Pavel_Anna on 8/2/2017.
 */

public class EducationCardPoolAdapter extends RecyclerView.Adapter{

    Context mContext;
    OnNewDataSet onNewDataSetEvent;

    int mImageIDs[] = {
            R.drawable.military_layout_pic,

    };

    LinkedHashMap<Integer,Integer> dataset;

    public EducationCardPoolAdapter(Context mContext, EducationCardPool parentFragment, OnNewDataSet listener, LayoutLoader layoutLoader) {
        this.mContext = mContext;
        onNewDataSetEvent = listener;
        dataset = new LinkedHashMap<>();
        dataset.put(R.drawable.military_layout_pic,R.layout.education_military_service_layout);

    }

    public class mEducationCardPoolHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;


        public mEducationCardPoolHolder(View itemView) {
            super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.pool_img);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mEducationCardPoolHolder mEducationCardPoolHolder = new mEducationCardPoolHolder(LayoutInflater.from(mContext).inflate(R.layout.card_pool_education_holder, parent, false));
        try {
            mEducationCardPoolHolder.imageView.setImageResource(mImageIDs[viewType]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mEducationCardPoolHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewDataSetEvent.addToDataSet(dataset.get(mImageIDs[position]));
                DetailsActivity.mInstance.getSupportFragmentManager().popBackStack();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
