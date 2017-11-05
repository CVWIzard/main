package com.cvwizard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cvwizard.DetailsActivity;
import com.cvwizard.Interfaces.EditTextInterfaces;
import com.cvwizard.Interfaces.OnNewDataSet;
import com.cvwizard.adapters.EducationTabAdapter;
import com.cvwizard.app.R;
import com.cvwizard.utils.LayoutLoader;

import java.util.LinkedList;


/**
 * Created by Pavel on 7/26/2016.
 */

public class EducationTab extends Fragment  {


 public  LinkedList<View> mEducationDataSet;
    public  EducationTabAdapter educationDetailsAdapter;
    private LayoutInflater mLayoutInflater;
    public RecyclerView mRecyclerView;
    OnNewDataSet onNewDataSet;
    LayoutLoader layoutLoader;
    EditTextInterfaces editTextInterfaces;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEducationDataSet = new LinkedList<>();
        mLayoutInflater = getLayoutInflater(savedInstanceState);
        onNewDataSet = new OnNewDataSet() {
            @Override
            public void addToDataSet(int recourceID) {
                EducationTab.this.addToDataSet(recourceID);
            }

            @Override
            public void addToDataSet(String fileName) {
                EducationTab.this.addToDataSet(fileName);
            }

            @Override
            public void removeFromDataSet(int position) {
                EducationTab.this.removeFromDataSet(position);
            }
        };

        layoutLoader = new LayoutLoader(getContext());
        editTextInterfaces = new EditTextInterfaces() {
            @Override
            public void focusChange(TextInputEditText editText) {
             //   Log.i("Education retriven data",retriveWrittenData(mRecyclerView).keySet().toArray().toString() + retriveWrittenData(mRecyclerView).values().toArray() + "");
              //  Log.i("Education retriven data","Recycler Child Count: "  + String.valueOf(mRecyclerView.getAdapter().getItemCount()));

            }
        };

    }

    public OnNewDataSet getOnNewDataSet() {
        return onNewDataSet;
    }

    public void addToDataSet(int viewRecource){
        mEducationDataSet.add(mLayoutInflater.inflate(viewRecource,null));
      //  mEducationDataSet.add(createDynamicViewFromJson(layoutLoader.getJsonFile("military_layout")));
        educationDetailsAdapter.notifyDataSetChanged();
    }

    public void addToDataSet(String filename){
        //  mEducationDataSet.add(mLayoutInflater.inflate(viewRecource,null));
        mEducationDataSet.add(((DetailsActivity) getActivity()).viewCreator.createDynamicViewFromJson(layoutLoader.getJsonFile(filename),editTextInterfaces));
        educationDetailsAdapter.notifyDataSetChanged();
    }



    public void removeFromDataSet(int position){
        mEducationDataSet.remove(position);
        educationDetailsAdapter.notifyDataSetChanged();
    }

    public  View getFromDataSet(int position){
        return mEducationDataSet.get(position);

    }


    //TODO // FIXME: 7/27/2016
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup holder = (ViewGroup) inflater.inflate(R.layout.education_main_holder,null);
         mRecyclerView = (RecyclerView) holder.findViewById(R.id.educaton_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mEducationDataSet.add(inflater.inflate(R.layout.education_academy_info,null));
        educationDetailsAdapter = new EducationTabAdapter(getContext(),this,mEducationDataSet,editTextInterfaces);
        mRecyclerView.setAdapter(educationDetailsAdapter);

        return holder;
    }

    public ArrayMap<String,LinkedList> retriveWrittenData(RecyclerView recyclerView){
        ArrayMap<String,LinkedList> viewsArray = new ArrayMap<>();
        if(recyclerView == null){
            return viewsArray;
        }
        try {
            for(int i = 0; i < recyclerView.getAdapter().getItemCount(); i++){
                LinkedList<String> viewContent = new  LinkedList<String>();
                for(int x = 0; x < ((CardView) recyclerView.getLayoutManager().findViewByPosition(i)).getChildCount(); x++){
                    CardView parent = ((CardView) recyclerView.getLayoutManager().findViewByPosition(i));
                    LinearLayout parentLayout = (LinearLayout) parent.getChildAt(1);
                    if(parentLayout.getChildAt(x) instanceof TextView){
                        viewContent.add(0, ((TextView) parentLayout.getChildAt(x)).getText().toString());
                    }else if (parentLayout.getChildAt(x) instanceof TextInputEditText){
                        if(((TextInputEditText) parentLayout.getChildAt(x)).getText().toString().length() > 1)
                        viewContent.add(((TextInputEditText) parentLayout.getChildAt(x)).getText().toString());
                    }
                    if(!viewContent.isEmpty())
                    viewsArray.put(viewContent.getFirst(),viewContent);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return viewsArray;
    }

}
