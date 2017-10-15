package com.cvwizard.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

import com.cvwizard.customViews.StyledTextView;
import com.cvwizard.fragments.SavedFilesFragment;
import com.cvwizard.app.R;

/**
 * Created by Pavel_Anna on 9/24/2017.
 */

public class FilesAdapter extends RecyclerView.Adapter {


    Context mContext;
    SavedFilesFragment mParentFragment;

    public FilesAdapter(SavedFilesFragment parentFragment, Context context) {
        mContext = context;
        mParentFragment = parentFragment;
    }

    class FileVH extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

       public ImageView fileImg;
       public StyledTextView fileText;
        int position;

        public FileVH(View itemView) {
            super(itemView);
            itemView.setOnCreateContextMenuListener(this);
            fileImg = (ImageView) itemView.findViewById(R.id.file_img);
            fileText = (StyledTextView) itemView.findViewById(R.id.file_text);
        }




        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(R.string.context_menu_title);
            MimeTypeMap myMime = MimeTypeMap.getSingleton();
            Intent openFileIntent = new Intent(Intent.ACTION_VIEW);
            String mimeType = myMime.getMimeTypeFromExtension(fileExt(mParentFragment.getFileList()[position].getAbsolutePath()));
            openFileIntent.setDataAndType(Uri.fromFile(mParentFragment.getFileList()[position]),mimeType);
            openFileIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            openFileIntent.putExtra("contextual","openfile");
            menu.add(0,v.getId(),0,R.string.context_menu_openfile).setIntent(openFileIntent);
            Intent deleteFileIntent = new Intent();
            deleteFileIntent.putExtra("contextual",position);
            menu.add(0,v.getId(),1,R.string.context_menu_deletefile).setIntent(deleteFileIntent);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FileVH fileVH = new FileVH(View.inflate(mContext,R.layout.file_layout,null));
        return fileVH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mParentFragment.getFileList()[position].getName().contains("pdf")){
            ((FileVH) holder).fileImg.setImageResource(R.drawable.ic_pdf_outline);
            ((FileVH) holder).fileImg.setColorFilter(ContextCompat.getColor(mContext,R.color.color_red));
            ((FileVH) holder).fileText.setText(mParentFragment.getFileList()[position].getName());
            ((FileVH) holder).position = position;
            Log.i(FilesAdapter.class.getSimpleName(),"filename: " + mParentFragment.getFileList()[position].getName());
        }else if(mParentFragment.getFileList()[position].getName().contains("doc")){
            ((FileVH) holder).fileImg.setImageResource(R.drawable.ic_doc_outline);
            ((FileVH) holder).fileImg.setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary));
            ((FileVH) holder).fileText.setText(mParentFragment.getFileList()[position].getName());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    private String fileExt(String url) {
        if (url.indexOf("?") > -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.lastIndexOf(".") == -1) {
            return null;
        } else {
            String ext = url.substring(url.lastIndexOf(".") + 1);
            if (ext.indexOf("%") > -1) {
                ext = ext.substring(0, ext.indexOf("%"));
            }
            if (ext.indexOf("/") > -1) {
                ext = ext.substring(0, ext.indexOf("/"));
            }
            return ext.toLowerCase();

        }
    }




    @Override
    public int getItemCount() {

        try {
            return mParentFragment.getFileList().length;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
