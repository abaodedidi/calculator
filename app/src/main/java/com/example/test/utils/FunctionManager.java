package com.example.test.utils;

import android.content.Context;
import android.graphics.Paint;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FunctionManager {
    private TreeSet<Float> mDD = new TreeSet<>();//domain of definition
    private Map<Float, Float> mFuncMap = new HashMap<>();//mapping table
    private volatile static FunctionManager mFunctionManager;
    private Context mContext;

    private FunctionManager(Context context) {
        this.mContext = context;
    }

    public static FunctionManager getInstance(Context context) {
        if (mFunctionManager == null) {
            synchronized (FunctionManager.class) {
                if (mFunctionManager == null) {
                    mFunctionManager = new FunctionManager(context);
                }
            }
        }
        return mFunctionManager;
    }

    public void initSinData() {
        mDD.clear();
        for (float i = -360; i <= 450; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - sin(x));
        });
    }

    public void initCosData() {
        mDD.clear();
        for (float i = -360; i <= 360; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - cos(x));
        });
    }

    public void initTanData() {
        mDD.clear();
        for (float i = -360; i <= 360; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - tan(x));
        });
    }

    public void initLgData() {
        mDD.clear();
        for (float i = -300; i <= 300; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - lg(x));
        });
    }

    public void initInData() {
        mDD.clear();
        for (float i = -300; i <= 300; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - ln(x));
        });
    }

    public void initSqrtData() {
        mDD.clear();
        for (float i = 0; i <= 400; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - sqrt(x));
        });
    }

    public void initEXData() {
        mDD.clear();
        for (float i = -300; i <= 300; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - ex(x));
        });
    }

    public void initSquData() {
        mDD.clear();
        for (float i = -300; i <= 300; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - squ(x));
        });
    }

    public void initRecData() {
        mDD.clear();
        for (float i = -300; i <= 300; i++) {
            mDD.add(i);
        }
        mFuncMap.clear();
        mDD.forEach(x -> {
            mFuncMap.put(x + Constant.CENTER_X, Constant.CENTER_Y - rec(x));
        });
    }

    private float lg(Float x) {
        float y = (float) (100.f * Math.log10(x));
        return y;
    }

    private float ln(Float x) {
        float y = (float) (100.f * Math.log(x));
        return y;
    }

    private float sin(float x) {
        float y = (float) (100 * Math.sin(Math.PI / 180 * x));
        return y;
    }

    private float tan(float x) {
        float y = (float) (100 * Math.tan(Math.PI / 180 * x));
        return y;
    }

    private float cos(float x) {
        float y = (float) (100 * Math.cos(Math.PI / 180 * x));
        return y;
    }

    private float sqrt(float x) {
        float y = (float) (Math.sqrt(x));
        return y;
    }

    private float ex(float x) {
        float y = (float) (100f*(Math.pow(Math.exp(1), x/300f)));
        return y;
    }

    private float squ(float x) {
        float y = (float) (Math.pow(x-100, 2)/200)+200f;
        return y;
    }

    private float rec(float x) {
        if (x == 0) {
            return 0;
        }
        float y = (float) (1 / x)*1000;
        return y;
    }


    public Map<Float, Float> getFunMap() {
        return mFuncMap;
    }
}
