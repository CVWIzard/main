package com.cvwizard.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Pavel on 4/11/2017.
 */

public class CanvasDrawer {

    Paint paint;
    Canvas canvas;

    public CanvasDrawer() {
        canvas = new Canvas();
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(18);
    }


    public void drawLineOnCanvas(String text){
        canvas.drawText(text,0,0,paint);

    }

    public void drawLineOnCanvas(String text, Canvas canvas,int x, int y){
        canvas.drawText(text,x,y,paint);

    }


    public Canvas getCanvas() {
        return canvas;
    }
}
