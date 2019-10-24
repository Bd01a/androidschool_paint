package com.fed.androidschool_paint.data;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

public class Polygon implements Drawn {

    private List<PointF> mPoints;

    private Paint mPaint;

    public Polygon(Paint paint){
        mPoints = new ArrayList<>();
        setPaint(paint);
    }


    @Override
    public void draw(Canvas canvas) {
        if(mPoints.size()>1) {
            for (int i = 1; i < mPoints.size(); i++) {
                canvas.drawLine(mPoints.get(i - 1).x, mPoints.get(i - 1).y, mPoints.get(i).x, mPoints.get(i).y, mPaint);
            }
            canvas.drawLine(mPoints.get(0).x, mPoints.get(0).y, mPoints.get(mPoints.size()-1).x, mPoints.get(mPoints.size()-1).y, mPaint);

        }

    }

    public void add(PointF point){
        mPoints.add(point);
    }

    public void add(int index, PointF point){
        mPoints.add(index, point);
    }

    public PointF get(int index){
        return mPoints.get(index);
    }
    public  int size(){
        return mPoints.size();
    }

    private void setPaint(Paint paint) {
        mPaint = new Paint(paint);
        mPaint.setStyle(Paint.Style.STROKE);
    }
}
