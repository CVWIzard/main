package com.cvwizard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.cvwizard.fragments.TemplateScreen;
import com.cvwizard.app.R;

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
      //  listView = (ListView) findViewById(R.id.lastFilesList);
      //  LastFilesListAdapter listAdapter = new LastFilesListAdapter(this);
      //  listView.setAdapter(listAdapter);

        getSupportFragmentManager().beginTransaction().add(R.id.temp_container,templateScreen,null).commit();
    }
}
