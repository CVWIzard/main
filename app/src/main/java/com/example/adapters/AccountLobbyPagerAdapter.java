package com.example.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.example.fragments.TemplateScreen;
import com.example.fragments.WipFragment;
import com.example.pavel.cvwizard.R;

/**
 * Created by Pavel_Anna on 9/24/2017.
 */

public class AccountLobbyPagerAdapter extends FragmentPagerAdapter {

    Fragment[] fragments = new Fragment[4];
    Context mContext;

    public AccountLobbyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        fragments[0] = new TemplateScreen();
        fragments[1] = new WipFragment();
        fragments[2] = new WipFragment();
        fragments[3] = new WipFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }




}
