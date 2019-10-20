package com.fed.androidschool_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.fed.androidschool_paint.data.Drawable;
import com.fed.androidschool_paint.data.Line;
import com.fed.androidschool_paint.data.Point;
import com.fed.androidschool_paint.data.Rectangle;

import java.util.ArrayList;
import java.util.List;


public class PaintView extends View {

    public static final int DRAW_POINT=1;
    public static final int DRAW_LINE=2;
    public static final int DRAW_RECT=3;

    private List<ColorPaint> mColorPaints;
    private Paint mCurrentPaint;
    private List<Drawable> mDrawables;

    private int mDrawType = DRAW_POINT;

    public PaintView(Context context) {
        super(context);
        init();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void setDrawType(int drawType){
        mDrawType = drawType;
    }

    private void init() {
        mDrawables = new ArrayList<>();

        mColorPaints = new ArrayList<>();
        mColorPaints.add(new ColorPaint(initPaint(R.color.black_color), getResources().getString(R.string.black)));
        mColorPaints.add(new ColorPaint(initPaint(R.color.red_color), getResources().getString(R.string.red)));
        mColorPaints.add(new ColorPaint(initPaint(R.color.blue_color), getResources().getString(R.string.blue)));
    }

    private Paint initPaint(int colorRes) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getResources().getDimension(R.dimen.paint_width));
        paint.setColor(getResources().getColor(colorRes));
        return paint;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (Drawable drawable:mDrawables) {
            drawable.draw(canvas);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (mDrawType){
            case DRAW_POINT:
                return pointEvent(event);
            case DRAW_LINE:
                return lineEvent(event);
            case DRAW_RECT:
                return rectEvent(event);
            default:
                return false;
        }

    }

    private boolean rectEvent(MotionEvent event) {
        PointF pointEvent = new PointF(event.getX(), event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Rectangle rect= new Rectangle(pointEvent, pointEvent, mCurrentPaint);
                mDrawables.add(rect);
                return true;
            case MotionEvent.ACTION_MOVE:
                Rectangle rectangle = (Rectangle)mDrawables.get(mDrawables.size()-1);
                rectangle.setEnd(pointEvent);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                return false;
        }
        invalidate();
        return true;
    }

    private boolean lineEvent(MotionEvent event) {

        PointF pointEvent = new PointF(event.getX(), event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Line line= new Line(pointEvent, pointEvent, mCurrentPaint);
                mDrawables.add(line);
                return true;
            case MotionEvent.ACTION_MOVE:
                Line lineCur = (Line)mDrawables.get(mDrawables.size()-1);
                lineCur.setEnd(pointEvent);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                return false;
        }
        invalidate();
        return true;

    }

    private boolean pointEvent(MotionEvent event) {
        PointF pointEvent = new PointF(event.getX(), event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Path path = new Path();
                path.moveTo(pointEvent.x,pointEvent.y);
                mDrawables.add(new Point(path, mCurrentPaint));
                return true;
            case MotionEvent.ACTION_MOVE:
                Point point = (Point)mDrawables.get(mDrawables.size()-1);
                point.getPath().lineTo(pointEvent.x,pointEvent.y);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                return false;
        }
        invalidate();
        return true;
    }

    public List<String> getNamesColorPaints() {
        List<String> names = new ArrayList<>();
        for (ColorPaint color:mColorPaints             ) {
            names.add(color.getName());
        }
        return names;
    }

    public void setPaint(int index){
        mCurrentPaint = mColorPaints.get(index).getPaint();
    }

    public void clear(){
        mDrawables = new ArrayList<>();
        invalidate();
    }



    class ColorPaint{
        private Paint mPaint;
        private String mName;

        public ColorPaint(Paint mPaint, String mName) {
            this.mPaint = mPaint;
            this.mName = mName;
        }

        public Paint getPaint() {
            return mPaint;
        }

        public void setPaint(Paint mPaint) {
            this.mPaint = mPaint;
        }

        public String getName() {
            return mName;
        }

        public void setName(String mName) {
            this.mName = mName;
        }
    }
}
