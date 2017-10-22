package com.cvwizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cvwizard.adapters.AccountLobbyPagerAdapter;
import com.cvwizard.app.R;
import com.cvwizard.customViews.StyledTextView;
import com.linkedin.platform.LISession;
import com.linkedin.platform.LISessionManager;
import com.squareup.picasso.Picasso;

public class AccountLobby extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager mPager;
    AccountLobbyPagerAdapter mAccountLobbyPagerAdapter;
    RelativeLayout[] mRelativeLayouts = new RelativeLayout[4];
    ImageView mProfilePicture;
    StyledTextView mProfileText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_lobby);
        mProfilePicture = (ImageView) findViewById(R.id.profile_pic);
        mProfileText = (StyledTextView) findViewById(R.id.profile_text);
        if(getIntent().getStringExtra("pictureUrl") != "null")
        Picasso.with(this).load(getIntent().getStringExtra("pictureUrl")).into(mProfilePicture);
        if(getIntent().getStringExtra("fullName") != "null")
        mProfileText.setText("Hello " + getIntent().getStringExtra("fullName"));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
        mRelativeLayouts[0] = (RelativeLayout) findViewById(R.id.account_lobby_create);
        mRelativeLayouts[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                mPager.setCurrentItem(position);
            }
        });
        mRelativeLayouts[1] = (RelativeLayout) findViewById(R.id.account_lobby_share);
        mRelativeLayouts[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                mPager.setCurrentItem(position);
            }
        });
        mRelativeLayouts[2] = (RelativeLayout) findViewById(R.id.account_lobby_char);
        mRelativeLayouts[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                mPager.setCurrentItem(position);
            }
        });
        mRelativeLayouts[3] = (RelativeLayout) findViewById(R.id.account_lobby_goto);
        mRelativeLayouts[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(v.getTag().toString());
                mPager.setCurrentItem(position);
            }
        });
        mPager = (ViewPager) findViewById(R.id.account_lobby_pager);
        mAccountLobbyPagerAdapter = new AccountLobbyPagerAdapter(getSupportFragmentManager(),this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountLobby.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setLayoutSelected(mRelativeLayouts[position],mRelativeLayouts);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_lobby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setLayoutSelected(RelativeLayout layout, RelativeLayout[] layouts){
        for(int i = 0 ; i < layouts.length; i++){
            layouts[i].setBackgroundColor(ContextCompat.getColor(this,R.color.white));
            ((ImageView) layouts[i].getChildAt(0)).setColorFilter(ContextCompat.getColor(this,R.color.dark_grey));
        }
        ((ImageView) layout.getChildAt(0)).setColorFilter(ContextCompat.getColor(this,R.color.pager_topic));
        layout.setBackgroundColor(ContextCompat.getColor(this,R.color.color_grey));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPager.setOffscreenPageLimit(3);
        mPager.setAdapter(mAccountLobbyPagerAdapter);
    }


}
