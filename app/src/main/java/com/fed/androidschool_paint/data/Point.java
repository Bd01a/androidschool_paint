package com.fed.androidschool_paint.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Parcel;



public class Point implements Drawn {
    Path mPath;
    private Paint mPaint;
    //private float[][] points;

    public Point(Path mPath, Paint mPaint) {
        this.mPath = mPath;
        this.mPaint = mPaint;
    }

    public void moveTo(){

    }

    public Point(Paint mPaint) {

        this.mPaint = mPaint;
    }

    protected Point(Parcel in) {
    }


    public Path getPath() {
        return mPath;
    }

    private void setPath(Path mPath) {
        this.mPath = mPath;
    }

    private Paint getPaint() {
        return mPaint;
    }

    private void setPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(getPath(), getPaint());
    }

}
