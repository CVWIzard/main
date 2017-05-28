package com.example.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dialogs.Temp_screen_dialog;
import com.example.pavel.cvwizard.DetailsActivity;
import com.example.pavel.cvwizard.R;
import com.example.utils.Profession;

/**
 * Created by Pavel on 6/15/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TempViewHolder> {

Context context;
    Profession profession;
    FragmentManager fragmentManager;

       public RecyclerAdapter (Context context, FragmentManager fragmentManager) {
        this.context = context;
           this.fragmentManager = fragmentManager;
    }


    Integer[] mImageIDs = {
            R.drawable.htech1,
            R.drawable.emergency,
            R.drawable.sales1,
            R.drawable.security
    };

    String mImageTags[] = {
            "High-tech",
            "Emergency",
            "Sales",
            "Security",

    };

    public static class TempViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public CardView mCardViewHolder;


        public TempViewHolder(View v) {
            super(v);
            mCardViewHolder = (CardView) v;
        }
    }


    @Override
    public TempViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_child, parent, false);

        TempViewHolder temp_vh = new TempViewHolder(v);
        return temp_vh;
    }

    @Override
    public void onBindViewHolder(TempViewHolder holder, final int position) {
       final AppCompatImageView imageView = (AppCompatImageView) holder.mCardViewHolder.findViewById(R.id.temp_content_image);
        final AppCompatTextView textView = (AppCompatTextView) holder.mCardViewHolder.findViewById(R.id.professionText);
        imageView.setImageResource(mImageIDs[position]);
        textView.setText(mImageTags[position]);


         holder.mCardViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,textView.getText().toString() + " has cliked",Toast.LENGTH_SHORT ).show();
                profession = new Profession(textView.getText().toString());
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // Temp_screen_dialog dialog = new Temp_screen_dialog();
             //  dialog.show(fragmentManager,"TAG");
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mImageIDs.length;
    }
}
