package com.example.test.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class
 */
public class Utils {
    public static boolean isNum(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.REGEX_NUM);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isRealNum(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile(Constant.REGEX_REAL_NUMBER);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isNoNumAfter(char c) {
        for (int i = 0; i < Constant.noNumAfter.length; i++) {
            if (Constant.noNumAfter[i] == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExtrOpera(char c) {
        for (int i = 0; i < Constant.operaSymbol.length; i++) {
            if (Constant.operaSymbol[i] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获得屏幕高度
     *
     * @param ctx 上下文
     * @param winSize 屏幕尺寸
     */
    public static void loadWinSize(Context ctx, Point winSize) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        if (wm != null) {
            wm.getDefaultDisplay().getMetrics(outMetrics);
        }
        winSize.x = outMetrics.widthPixels;
        winSize.y = outMetrics.heightPixels-200;
    }

    public static boolean isOpera(char c) {
        for (int i = 0; i < Constant.baseOpera.length; i++) {
            if (Constant.baseOpera[i] == c) {
                return true;
            }
        }
        return false;
    }

    public static boolean containOpera(String mathStr) {
        return mathStr.contains("+") || mathStr.contains("-") || mathStr.contains("×") || mathStr.contains("÷");
    }

    public static void showToast(Context context, String value) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    }
}
