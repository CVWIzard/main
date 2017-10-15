package com.cvwizard.utils;

import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;

import com.cvwizard.DetailsActivity;
import com.cvwizard.storage.StorageClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Pavel on 4/11/2017.
 */

public class PDFCreator {



    DetailsActivity mDetailsActivity;
    int pageNumber = 1;
    PdfDocument pdfDocument;
    CanvasDrawer canvasDrawer;
    private String mFileName;


    public PDFCreator(DetailsActivity detailsActivity) {
        mDetailsActivity = detailsActivity;
        pdfDocument = new PdfDocument();

    }

    public PDFCreator(DetailsActivity detailsActivity, CanvasDrawer canvasDrawer) {
        mDetailsActivity = detailsActivity;
        pdfDocument = new PdfDocument();
        this.canvasDrawer = canvasDrawer;

    }


    public void createWrittenPage(int pageNumber,ArrayMap<String,String> dataMap,String fileName){

        mFileName = fileName + ".pdf";
        PageInfo pageInfo = new PageInfo.Builder(mDetailsActivity.mSectionsPagerAdapter.personalDetailsTab.getView().getWidth(),mDetailsActivity.mSectionsPagerAdapter.personalDetailsTab.getView().getHeight(), 5).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
       // View content = mDetailsActivity.mSectionsPagerAdapter.personalDetailsTab.getView();
        View content = mDetailsActivity.webViewFragment.mWebView;
       /* for (int i = 0; i<dataMap.size(); i++) {
            canvasDrawer.drawLineOnCanvas(dataMap.keyAt(i),page.getCanvas(),100, i * 100);
            canvasDrawer.drawLineOnCanvas(dataMap.valueAt(i),page.getCanvas(),200,i* 100);
        }*/
       content.draw(page.getCanvas());

        pdfDocument.finishPage(page);
        try {
            //File f = new File(Environment.getExternalStorageDirectory().getPath() +  "/CV.Wizard.pdf");
            File f = new File(StorageClass.getValues("outfolderpath") + "/" + mFileName);
            Log.i("FilePath",f.getPath());
           /* if(!f.exists()) {
                f.getParentFile().mkdirs();
                return;
            }*/
            FileOutputStream fos = new FileOutputStream(f);
            pdfDocument.writeTo(fos);
            pdfDocument.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





// create a new page from the PageInfo

}
