package com.example.test.utils;

import java.util.Arrays;
import java.util.List;

/**
 * 常量类
 */
public class Constant {

    private final static String[] symbol=new String[]{"sin","cos","tan","e^x","x^y","√x","log","ln"};
    public final static String REGEX_NUM = "[0-9]"; //是否是0～9
    public final static char[] operaSymbol=new char[]{'+','-','x','÷'};

    public final static char[] noNumAfter=new char[]{'e','π',')'};


    public static List<String> getSymbols(){
        return Arrays.asList(symbol);
    }
}
