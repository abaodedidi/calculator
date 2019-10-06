package com.example.test.utils;

import java.util.Arrays;
import java.util.List;

public class Constant {

    private final static String[] symbol=new String[]{"sin","cos","tan","x^2","e^x","x^y","√x","log","ln"};

    public static List<String> getSymbols(){
        return Arrays.asList(symbol);
    }
}
