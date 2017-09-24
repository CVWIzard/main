package com.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.FilesAdapter;
import com.example.storage.StorageClass;

import java.io.File;

/**
 * Created by Pavel_Anna on 9/24/2017.
 */

public class SavedFilesFragment extends Fragment {


    RecyclerView mFilesView;

    public File[] getFileList(){
        String path = StorageClass.getValues("outfolderpath");
        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        return files;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mFilesView = new RecyclerView(getContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false);
        mFilesView.setLayoutManager(layoutManager);
        FilesAdapter filesAdapter = new FilesAdapter(this,getContext());
        registerForContextMenu(mFilesView);
        mFilesView.setAdapter(filesAdapter);
        return mFilesView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getIntent() != null) {
            if (item.getIntent().getStringExtra("contextual") != null && item.getIntent().getStringExtra("contextual").toString().equals("openfile")) {
                startActivity(item.getIntent());
            } else {
                int position = item.getIntent().getIntExtra("contextual", 9999);
                if (position == 9999) {
                    Snackbar.make(mFilesView, "Oops file not found", Snackbar.LENGTH_SHORT);
                    return false;
                }
                File myFile = getFileList()[position];
                if (myFile.exists())
                    myFile.delete();
                mFilesView.getAdapter().notifyItemRemoved(position);
            }
        }
        return super.onContextItemSelected(item);
    }
}
