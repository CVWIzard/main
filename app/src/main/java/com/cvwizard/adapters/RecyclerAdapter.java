package com.cvwizard.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cvwizard.DetailsActivity;
import com.cvwizard.app.R;
import com.cvwizard.utils.Profession;

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
            R.drawable.hi_tech,
            R.drawable.medic,
            R.drawable.salesman,
            R.drawable.security_man,

    };

    String mImageTags[] = {
            "High-tech",
            "Emergency",
            "Sales",
            "Security",


    };

    public static class TempViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public FrameLayout mCardViewHolder;


        public TempViewHolder(View v) {
            super(v);
            mCardViewHolder = (FrameLayout) v;
        }
    }


    @Override
    public TempViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       /* View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.template_child, parent, false);*/
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_screen_activity_new_viewholder,parent,false);

        TempViewHolder temp_vh = new TempViewHolder(v);
        return temp_vh;
    }

    @Override
    public void onBindViewHolder(TempViewHolder holder, final int position) {
       final ImageView imageView = (ImageView) holder.mCardViewHolder.findViewById(R.id.temp_activity_holder_image);
        final TextView textView = (TextView) holder.mCardViewHolder.findViewById(R.id.temp_activity_holder_text);
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
