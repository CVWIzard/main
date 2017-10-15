package com.cvwizard.customViews;


import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Pavel_Anna on 5/28/2017.
 */

public class StyledTextView extends android.support.v7.widget.AppCompatTextView {


    public StyledTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"font/Quicksand-Regular.ttf");
        this.setTypeface(typeface);
    }
}
