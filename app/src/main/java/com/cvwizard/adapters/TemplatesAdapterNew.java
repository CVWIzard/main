package com.cvwizard.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cvwizard.customViews.StyledTextView;
import com.cvwizard.app.R;

/**
 * Created by Pavel on 7/21/2017.
 */

public class TemplatesAdapterNew extends RecyclerView.Adapter {

    Integer[] mImageIDs = {
            R.drawable.htech1,
            R.drawable.emergency
    };

    String mImageTags[] = {
            "High-tech",
            "Emergency",
            "Sales",
            "Security",

    };

    public class TempNewViewHolder extends RecyclerView.ViewHolder{

        public ImageView getTempNewViewHolderImage() {
            return TempNewViewHolderImage;
        }

        public StyledTextView getTempNewViewHolderText() {
            return TempNewViewHolderText;
        }

        private ImageView TempNewViewHolderImage;
        private StyledTextView TempNewViewHolderText;
        private FrameLayout mTempViewHolder;


        public TempNewViewHolder(View itemView) {
            super(itemView);
            mTempViewHolder = (FrameLayout) itemView;
            TempNewViewHolderImage = (ImageView) mTempViewHolder.findViewById(R.id.temp_activity_holder_image);
            TempNewViewHolderText = (StyledTextView) mTempViewHolder.findViewById(R.id.temp_activity_holder_text);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_screen_activity_new_viewholder, parent, false);

        TempNewViewHolder tempNewViewHolder = new TempNewViewHolder(v);
        return tempNewViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TempNewViewHolder) holder).TempNewViewHolderText.setText(mImageTags[position]);
    }

    @Override
    public int getItemCount() {
        return mImageIDs.length - 1;
    }
}
