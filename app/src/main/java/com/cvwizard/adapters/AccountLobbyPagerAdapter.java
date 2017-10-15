package com.cvwizard.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cvwizard.fragments.SavedFilesFragment;
import com.cvwizard.fragments.TemplateScreen;
import com.cvwizard.fragments.WipFragment;

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
        fragments[1] = new SavedFilesFragment();
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
