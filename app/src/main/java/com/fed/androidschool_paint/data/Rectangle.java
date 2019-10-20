package com.fed.androidschool_paint.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class Rectangle implements Drawable{
    private PointF mStart;
    private PointF mEnd;

    private Paint mPaint;

    public Rectangle() {
        mStart = new PointF();
        mEnd = new PointF();
    }

    public Rectangle(PointF start, PointF end, Paint paint) {
        this.mStart = start;
        this.mEnd = end;
        mPaint = paint;
    }

    private RectF getRectF(){
        return new RectF(getLeft(),getTop(),getRight(),getBottom());
    }

    private float getLeft(){
        return Math.min(mStart.x,mEnd.x);
    }

    private float getRight(){
        return Math.max(mStart.x,mEnd.x);
    }

    private float getTop(){
        return Math.min(mStart.y,mEnd.y);
    }

    private float getBottom(){
        return Math.max(mStart.y,mEnd.y);
    }

    private PointF getStart() {
        return mStart;
    }

    private void setStart(PointF start) {
        this.mStart = start;
    }

    private PointF getEnd() {
        return mEnd;
    }

    public void setEnd(PointF end) {
        this.mEnd = end;
    }



    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(getRectF(), mPaint);
    }
}
