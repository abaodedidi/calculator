package com.example.test.utils;

import android.content.Context;
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
