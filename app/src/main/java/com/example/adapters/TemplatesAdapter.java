package com.example.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.pavel.cvwizard.R;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by Pavel on 6/9/2016.
 */

public class TemplatesAdapter implements ListAdapter {

    Context context;
    Integer[] mImageIDs = {

    };

    String mImageTags[] = {
            "Waiter",
            "customer_service",
            "whore",
            "robber",
            "bank",
            "security",
            "CEO"
    };


  public TemplatesAdapter(Context context){
        this.context = context;

    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mImageIDs.length - 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (CardView) View.inflate(context,R.layout.template_child,null);
        convertView.findViewById(R.id.temp_content_image).setBackgroundResource(mImageIDs[position]);
        convertView.findViewById(R.id.temp_content_image).setTag(mImageTags[position]);


        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
