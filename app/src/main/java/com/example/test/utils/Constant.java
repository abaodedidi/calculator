package com.example.test.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Constant class
 */
public class Constant {

    public final static int DEFAULT_PRECISION = 4;
    public final static int DEFAULT_FUNC = 0;
    public final static String DEFAULT_CALCULATOR_VALUE = "0";
    public final static boolean DEFAULT_IS_FIRST_IN = true;
    public final static String ERROR = "Error";
    private final static String[] symbol = new String[]{"sin", "cos", "tan", "e^x", "x^y", "√x", "1/x", "log", "ln"};
    public final static String REGEX_NUM = "[0-9]"; //是否是0～9
    public final static String REGEX_REAL_NUMBER = "^[-\\+]?(\\d+|\\d+\\.\\d+)$"; //是否是实数
    public final static char[] operaSymbol=new char[]{'+', '-', '×', '÷', '(', ')', '#'};

    public final static char[] baseOpera=new char[]{'+', '-', '×', '÷'};

    public final static char[] noNumAfter=new char[]{'e','π',')'};
    public final static String[] precisionArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    public final static String[] funcArray = new String[]{"sin","cos","tan","e^x","√x","log","ln","x^2","1/x"};
    public final static int CENTER_X = 550;
    public final static int CENTER_Y = 550;

    public final static int FUNC_TYPE_SIN = 0;
    public final static int FUNC_TYPE_COS = 1;
    public final static int FUNC_TYPE_TAN = 2;
    public final static int FUNC_TYPE_EX = 3;
    public final static int FUNC_TYPE_SQRT = 4;
    public final static int FUNC_TYPE_LOG = 5;
    public final static int FUNC_TYPE_LN = 6;
    public final static int FUNC_TYPE_SQU = 7;
    public final static int FUNC_TYPE_REC = 8;




    public static List<String> getSymbols(){
        return Arrays.asList(symbol);
    }
}
