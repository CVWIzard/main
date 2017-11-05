package com.cvwizard.adapters;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cvwizard.Interfaces.EditTextInterfaces;
import com.cvwizard.fragments.EducationTab;
import com.cvwizard.app.R;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * Created by Pavel on 8/4/2016.
 */

public class EducationTabAdapter extends RecyclerView.Adapter<EducationTabAdapter.mEducationViewHolder> {

    protected Context mContext;
    EducationTab educationTab;
    LinkedList<View> mDataset;
    private EditTextInterfaces mEditTextInterfaces;
    LinkedHashMap<String,Map> mEducationValueArray;


    public EducationTabAdapter(Context context, EducationTab parentFragment, LinkedList<View> dataset, EditTextInterfaces editTextInterfaces) {
        mContext = context;
        educationTab = parentFragment;
        mDataset = dataset;
        mEditTextInterfaces = editTextInterfaces;
        mEducationValueArray = new LinkedHashMap<>();
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
    public void onViewRecycled(mEducationViewHolder holder) {
        super.onViewRecycled(holder);
        retriveDataFromViews(holder,mEducationValueArray);
    }

    public LinkedList<View> getmDataset() {
        return mDataset;
    }

    public LinkedHashMap<String, Map> getmEducationValueArray() {
        return mEducationValueArray;
    }

    @Override
    public mEducationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new mEducationViewHolder(mDataset.get(viewType));


    }

    @Override
    public void onBindViewHolder(mEducationViewHolder holder, final int position) {
        try {
            holder.cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                educationTab.getOnNewDataSet().removeFromDataSet(position);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

      //  getLastTextViewFromHolder(holder);

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

    public TextInputEditText getLastTextViewFromHolder(mEducationViewHolder holder){
        LinearLayout parent = (LinearLayout) ((CardView) holder.itemView).getChildAt(1);
        if(parent.getChildAt(parent.getChildCount() -1) != null && parent.getChildAt(parent.getChildCount() -1) instanceof TextInputEditText){
            return (TextInputEditText) parent.getChildAt(parent.getChildCount() -1);
        }else if (((LinearLayout) parent.getChildAt(parent.getChildCount() -1)).getChildAt(0) != null && ((LinearLayout) parent.getChildAt(parent.getChildCount() -1)).getChildAt(0) instanceof LinearLayout){
            LinearLayout linearLayout = (LinearLayout) ((LinearLayout) parent.getChildAt(parent.getChildCount() -1)).getChildAt(0);
            linearLayout.getChildCount();
           return  null;
        }else if (parent.getChildAt(parent.getChildCount() -2) != null && parent.getChildAt(parent.getChildCount() -2) instanceof TextInputEditText){
            return (TextInputEditText) parent.getChildAt(parent.getChildCount() -2);
        }else if (((LinearLayout) parent.getChildAt(parent.getChildCount() -2)).getChildAt(0) != null && ((LinearLayout) parent.getChildAt(parent.getChildCount() -2)).getChildAt(0) instanceof LinearLayout){
            LinearLayout linearLayout = (LinearLayout) ((LinearLayout) parent.getChildAt(parent.getChildCount() -2)).getChildAt(0);
            linearLayout.getChildCount();
            return  null;
        }
        return null;
    }

    public void retriveDataFromViews(mEducationViewHolder holder, LinkedHashMap<String,Map> valuesSet){
        int semiCounter = 1;
        LinearLayout parent = (LinearLayout) ((CardView) holder.itemView).getChildAt(1);
        String key = "empty";
        HashMap<String,String> valuesMap = new LinkedHashMap<>();

        for(int i = 0 ; i <parent.getChildCount(); i++){
            if(parent.getChildAt(i) instanceof TextView){
                if(!((TextView) parent.getChildAt(i)).getText().toString().isEmpty()){
                    key = ((TextView) parent.getChildAt(i)).getText().toString();
                }
            }else if (((LinearLayout) parent.getChildAt(i)).getChildAt(0) instanceof TextInputEditText){
                TextInputLayout inputLayout = (TextInputLayout) parent.getChildAt(i);
                TextInputEditText inputEditText = ((TextInputEditText) ((LinearLayout) parent.getChildAt(i)).getChildAt(0));
                if(!inputEditText.getText().toString().isEmpty()){
                    valuesMap.put(inputLayout.getHint().toString(),inputEditText.getText().toString());
                }

            }else if(((LinearLayout) parent.getChildAt(i)).getChildAt(0) instanceof LinearLayout){
                TextInputLayout dynamicLayout = (TextInputLayout) ((LinearLayout) parent.getChildAt(i)).getChildAt(0);
                EditText dynamicEditText = dynamicLayout.getEditText();
                valuesMap.put(dynamicLayout.getHint().toString(),dynamicEditText.getText().toString()
                );

            }
        }

        if(valuesSet.get(key) == null) {
            semiCounter = 0;
            valuesSet.put(key, valuesMap);
        }else{
            key = key + String.valueOf(semiCounter);
            valuesSet.put(key, valuesMap);
            semiCounter ++;
        }

    }


}



