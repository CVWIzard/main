package com.example.pavel.cvwizard;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.fragments.EducationTab;
import com.example.fragments.ExperienceTab;
import com.example.fragments.PersonalDetailsTab;
import com.example.utils.AppParameters;
import com.example.utils.CanvasDrawer;
import com.example.utils.PDFCreator;

import java.io.File;

public class DetailsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
   public static Toolbar toolbar;
    FloatingActionButton fab;
    public CanvasDrawer canvasDrawer;
    File f;

    private  final int REQUEST_EXTERNAL_STORAGE = 1;
    private  String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        findViewById(R.id.appbar).setBackgroundColor(ContextCompat.getColor(this,R.color.fabColor));
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mSectionsPagerAdapter);


         fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(canvasDrawer == null){
                    Snackbar.make(view, "Creating Canvass", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    CreateCanvas();
                }else {
                    Snackbar.make(view, "Saving data to Canvass", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    saveData();
                }
            }
        });

        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.white));
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.fabColor));
        toolbar.setTitle("CV.Wizard");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyStoragePermissions(this);
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );


        }else{
            if(f == null) {
                f = new File(Environment.getExternalStorageDirectory().getPath() + "/" + AppParameters.FILE_FOLDER_NAME);
            }
            if(!f.exists()){
                f.getParentFile().mkdirs();
            }

        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        PersonalDetailsTab personalDetailsTab = new PersonalDetailsTab();
        EducationTab educationTab = new EducationTab();
        ExperienceTab experienceTab = new ExperienceTab();


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1);

            //TODO // FIXME: 7/27/2016 
            switch(position){
                case 0:{
                    return personalDetailsTab;
                }
                case 1:{
                    return educationTab;
                }
                case 2:{
                    return experienceTab;
                }
                default:
                    return  PlaceholderFragment.newInstance(position + 1);
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Personal Details";
                case 1:
                    return "Education and Military Service";
                case 2:
                    return "Workplace Experience";
            }
            return null;
        }
    }

    public void CreateCanvas(){
        canvasDrawer = new CanvasDrawer();
    }

    public Canvas getCurrentCanvas(){
        return canvasDrawer.getCanvas();
    }

    public void saveData(){


        ArrayMap<String,String> stringArrayMap = mSectionsPagerAdapter.personalDetailsTab.retriveWrittenData();
        PDFCreator pdfCreator = new PDFCreator(this,canvasDrawer);
        pdfCreator.createWrittenPage(1,stringArrayMap);

    }
}
