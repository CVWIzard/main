package com.cvwizard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cvwizard.adapters.PersonalDetailsAdapter;
import com.cvwizard.app.R;

/**
 * Created by Pavel on 6/21/2016.
 */

public class PersonalDetailsTab extends Fragment {

    String[] mKeys,mValues = null;
    PersonalDetailsAdapter personalDetailsAdapter;
    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View holder = inflater.inflate(R.layout.fragment_personal_details_holder,null);
        CardView cardView = (CardView) holder.findViewById(R.id.personal_details_card_holder);

        recyclerView = new RecyclerView(getContext());
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        personalDetailsAdapter = new PersonalDetailsAdapter(getActivity().getApplication(),"General");
        recyclerView.setAdapter(personalDetailsAdapter);

        cardView.addView(recyclerView);
        return holder;
    }

    public ArrayMap<String,String> retriveWrittenData(){
        ArrayMap<String,String> arrayMap = new ArrayMap<>();
        mKeys = new  String[personalDetailsAdapter.getItemCount()];
        mValues = new  String[personalDetailsAdapter.getItemCount()];
        recyclerView.scrollTo(0,0);
        for(int i = 0; i < personalDetailsAdapter.profession.getPersonalValues().length; i++){
           /* if(mRecyclerView.getChildAt(i) instanceof LinearLayout){
                if(((LinearLayout) mRecyclerView.getChildAt(i)).getChildAt(0) instanceof TextInputLayout) {
                    if(((TextInputLayout) ((LinearLayout) mRecyclerView.getChildAt(i)).getChildAt(0)).getChildAt(0) instanceof TextInputEditText){
                        TextInputEditText inputEditText = ((TextInputEditText) ((TextInputLayout) ((LinearLayout) mRecyclerView.getChildAt(i)).getChildAt(0)).getChildAt(0));
                        Log.i(getClass().getSimpleName(), "Hint: " + inputEditText.getHint() + " Text: " + inputEditText.getText().toString());
                    }

                }
            }*/
           if(recyclerView.getLayoutManager().findViewByPosition(i) != null) {
               TextInputEditText inputEditText = ((TextInputEditText) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.detail_edittext));
               TextInputLayout inputEditTextLayout = ((TextInputLayout) recyclerView.getLayoutManager().findViewByPosition(i).findViewById(R.id.detail_edittext_layout));
               Log.i(getClass().getSimpleName(), "Hint: " + inputEditTextLayout.getHint() + " Text: " + inputEditText.getText().toString());
               mKeys[i] = inputEditTextLayout.getHint().toString();
               mValues[i] = inputEditText.getText().toString();
               arrayMap.put(mKeys[i],"'" + mValues[i] + "'");
           }
        }



        return arrayMap;
    }


}
