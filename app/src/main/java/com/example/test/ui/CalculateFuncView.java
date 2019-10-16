package com.example.test.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.test.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class CalculateFuncView extends View {
    private Paint mPaint;
    private Point mWinSize;//screen size
    private Point mOriCoo;//origin of coordinate system
    private Map<Float, Float> mFunMap = new HashMap<>();


    public CalculateFuncView(Context context) {
        this(context,null);
    }

    public CalculateFuncView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCalculateData();
    }

    private void initCalculateData(){
        mWinSize = new Point();
        Utils.loadWinSize(getContext(), mWinSize);
        mOriCoo = new Point(550, 550);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setMap(Map<Float, Float> funMap) {
        mFunMap.clear();
        mFunMap.putAll(funMap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas, mWinSize, mPaint);
        drawCoordinate(canvas, mOriCoo, mWinSize, mPaint);
        drawMap(canvas);
    }

    public static Path gridPath(int step, Point winSize) {
        Path path = new Path();
        for (int i = 0; i < winSize.y / step + 1; i++) {
            path.moveTo(0, step * i);
            path.lineTo(winSize.x, step * i);
        }
        for (int i = 0; i < winSize.x / step + 1; i++) {
            path.moveTo(step * i, 0);
            path.lineTo(step * i, winSize.y);
        }
        return path;
    }

    //draw grid
    public static void drawGrid(Canvas canvas, Point winSize, Paint paint) {
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(new DashPathEffect(new float[]{10, 5}, 0));
        canvas.drawPath(gridPath(50, winSize), paint);
    }

    //draw coordinate
    public static void drawCoordinate(Canvas canvas, Point coo, Point winSize, Paint paint) {
        paint.setStrokeWidth(4);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setPathEffect(null);
        //draw a straight line
        canvas.drawPath(coordinatePath(coo, winSize), paint);
        //left arrow
        canvas.drawLine(winSize.x, coo.y, winSize.x - 30, coo.y - 20, paint);
        canvas.drawLine(winSize.x, coo.y, winSize.x - 30, coo.y + 20, paint);
        //up arrow
        canvas.drawLine(coo.x, coo.y - 550, coo.x - 20, coo.y - 550+30, paint);
        canvas.drawLine(coo.x, coo.y - 550, coo.x + 20, coo.y - 550+30, paint);
        //draw text
        drawText(canvas, coo, winSize, paint);
    }
    //draw text
    private static void drawText(Canvas canvas, Point coo, Point winSize, Paint paint) {
        paint.setTextSize(50);
        canvas.drawText("x", winSize.x - 60, coo.y - 40, paint);
        canvas.drawText("y", coo.x - 40, coo.y - 500, paint);
        paint.setTextSize(25);
        for (int i = 1; i < (winSize.x - coo.x) / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(100 * i + "", coo.x - 20 + 100 * i, coo.y + 40, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x + 100 * i, coo.y, coo.x + 100 * i, coo.y - 10, paint);
        }
        for (int i = 1; i < coo.x / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(-100 * i + "", coo.x - 20 - 100 * i, coo.y + 40, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x - 100 * i, coo.y, coo.x - 100 * i, coo.y - 10, paint);
        }
        for (int i = 1; i < (winSize.y - coo.y) / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(100 * i + "", coo.x + 20, coo.y + 10 + 100 * i, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x, coo.y + 100 * i, coo.x + 10, coo.y + 100 * i, paint);
        }
        for (int i = 1; i < coo.y / 50; i++) {
            paint.setStrokeWidth(2);
            canvas.drawText(-100 * i + "", coo.x + 20, coo.y + 10 - 100 * i, paint);
            paint.setStrokeWidth(5);
            canvas.drawLine(coo.x, coo.y - 100 * i, coo.x + 10, coo.y - 100 * i, paint);
        }
    }

    //
    public static Path coordinatePath(Point coo, Point winSize) {
        Path path = new Path();
        //x positive semi axis
        path.moveTo(coo.x, coo.y);
        path.lineTo(winSize.x, coo.y);
        //x negative half axis
        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x - winSize.x, coo.y);
        //y positive semi axis
        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x, coo.y - winSize.y);
        //y negative half axis
        path.moveTo(coo.x, coo.y);
        path.lineTo(coo.x, winSize.y);
        return path;
    }

    public void drawMap(Canvas canvas) {
        mFunMap.forEach((k, v) -> {
            canvas.drawPoint(k, v, mPaint);
        });
    }

}
