package com.example.test.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Constant class
 */
public class Constant {

    public final static int DEFAULT_PRECISION = 4;
    public final static String DEFAULT_CALCULATOR_VALUE = "0";
    public final static boolean DEFAULT_IS_FIRST_IN = true;
    public final static String ERROR = "Error";
    private final static String[] symbol=new String[]{"sin","cos","tan","e^x","x^y","√x","log","ln"};
    public final static String REGEX_NUM = "[0-9]"; //是否是0～9
    public final static String REGEX_REAL_NUMBER = "^[-\\+]?(\\d+|\\d+\\.\\d+)$"; //是否是实数
    public final static char[] operaSymbol=new char[]{'+', '-', '×', '÷', '(', ')', '#'};

    public final static char[] baseOpera=new char[]{'+', '-', '×', '÷'};

    public final static char[] noNumAfter=new char[]{'e','π',')'};
    public final static String[] precisionArray = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};



    public static List<String> getSymbols(){
        return Arrays.asList(symbol);
    }
}
