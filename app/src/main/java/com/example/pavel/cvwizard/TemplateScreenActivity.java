package com.example.pavel.cvwizard;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.adapters.LastFilesListAdapter;
import com.example.fragments.TemplateScreen;

/**
 * Created by Pavel on 6/14/2016.
 */

public class TemplateScreenActivity extends AppCompatActivity {

    final TemplateScreen templateScreen = new TemplateScreen();
    ListView listView;
    String[] lastFileTemporary = {
            "Last files",
            " ",
            " ",
            " ",
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.templatescreenactivity);
        listView = (ListView) findViewById(R.id.lastFilesList);
        LastFilesListAdapter listAdapter = new LastFilesListAdapter(this);
        listView.setAdapter(listAdapter);

        getSupportFragmentManager().beginTransaction().add(R.id.temp_container,templateScreen,null).commit();
    }
}
