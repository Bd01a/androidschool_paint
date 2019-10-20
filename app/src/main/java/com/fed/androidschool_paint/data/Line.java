package com.fed.androidschool_paint.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class Line implements Drawable{
    private PointF mStart;
    private PointF mEnd;

    private Paint mPaint;

    public Line(PointF start, PointF end, Paint paint) {
        this.mStart = start;
        this.mEnd = end;
        mPaint = paint;
    }

    public Line(int startX, int startY, int endX, int endY) {
        this.mStart = new PointF(startX, startY);
        this.mEnd = new PointF(endX,endY);
    }

    public Line() {
        mStart = new PointF();
        mEnd = new PointF();

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

    private Paint getPaint() {
        return mPaint;
    }

    private void setPaint(Paint paint) {
        this.mPaint = paint;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(mStart.x,mStart.y,mEnd.x,mEnd.y,mPaint);
    }
}
