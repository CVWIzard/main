package com.cvwizard.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cvwizard.app.R;
import com.cvwizard.storage.StorageClass;
import com.cvwizard.utils.AppParameters;
import com.cvwizard.utils.Profession;

/**
 * Created by Pavel on 6/21/2016.
 */

public class PersonalDetailsAdapter extends RecyclerView.Adapter<PersonalDetailsAdapter.ViewHolder> {

  public  Profession profession;
    Context context;
    public PersonalDetailsAdapter(Context AppContext,String professionName){
        profession = new Profession(professionName);
        this.context = AppContext;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        public LinearLayout mLinearLayoutViewHolder;
        public ViewHolder(View v) {
            super(v);
            mLinearLayoutViewHolder = (LinearLayout) v;
        }
    }

    @Override
    public PersonalDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_details_child_texts, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TextInputLayout textInputLayout = (TextInputLayout) holder.mLinearLayoutViewHolder.findViewById(R.id.detail_edittext_layout);
        final TextInputEditText editText = (TextInputEditText) holder.mLinearLayoutViewHolder.findViewById(R.id.detail_edittext);
        textInputLayout.setHint(profession.getPersonalValues()[position]);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"font/Quicksand-Medium.ttf");
        textInputLayout.setTypeface(typeface);
        editText.setHighlightColor(ContextCompat.getColor(context,R.color.white));

        switch(position){
            case 0: editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.setText(StorageClass.getValues("fullname",""));
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            String text = ((TextInputEditText) v).getText().toString();
                            if(text.isEmpty()){
                                ((TextInputEditText) v).setError(context.getString(R.string.edittexts_error_message));
                            }else{
                                ((TextInputEditText) v).setError(null);
                            }
                        }
                    }
                });
                break;
            case 1: editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                editText.setText(StorageClass.getValues("email",""));
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            String text = ((TextInputEditText) v).getText().toString();
                            if(text.isEmpty() || !text.contains("@")){
                                ((TextInputEditText) v).setError(context.getString(R.string.edittexts_error_message));
                            }else{
                                ((TextInputEditText) v).setError(null);
                            }
                        }
                    }
                });
                break;
            case 2: editText.setInputType(InputType.TYPE_CLASS_PHONE);
            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus){
                        String text = ((TextInputEditText) v).getText().toString();
                        if(text.isEmpty()){
                            ((TextInputEditText) v).setError(context.getString(R.string.edittexts_error_message));
                        }else{
                            ((TextInputEditText) v).setError(null);
                        }
                    }
                }
            });
                break;
            case 3: editText.setInputType(InputType.TYPE_CLASS_DATETIME);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            String text = ((TextInputEditText) v).getText().toString();
                            if(text.isEmpty()){
                                ((TextInputEditText) v).setError(context.getString(R.string.edittexts_error_message));
                            }else{
                                ((TextInputEditText) v).setError(null);
                            }
                        }
                    }
                });
                break;
            case 4: editText.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(!hasFocus){
                            String text = ((TextInputEditText) v).getText().toString();
                            if(text.isEmpty()){
                                ((TextInputEditText) v).setError(context.getString(R.string.edittexts_error_message));
                            }else{
                                ((TextInputEditText) v).setError(null);
                            }
                        }
                    }
                });

        }


    }



    @Override
    public int getItemCount() {
        return profession.getPersonalValues().length;
    }
}
