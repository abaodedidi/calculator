package com.example.test.calculator;

import android.content.Context;

import com.example.test.utils.CalculateExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class ExampleUnitTest {
    private CalculateExecutor mCalculateExecutor;
    private Context mContext;
    @Before
    public void setUp(){
        mContext= Mockito.mock(Context.class);
        mCalculateExecutor=new CalculateExecutor(mContext);
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAdd(){
        double result=mCalculateExecutor.baseCalculate(1,'+',2);
        assertEquals(3, (int)result);
    }

    @Test
    public void testSub(){
        double result=mCalculateExecutor.baseCalculate(5,'-',3);
        assertEquals(2, (int)result);
    }

    @Test
    public void testMul(){
        double result=mCalculateExecutor.baseCalculate(3,'×',2);
        assertEquals(6, (int)result);
    }

    @Test
    public void testDiv(){
        double result=mCalculateExecutor.baseCalculate(8,'÷',4);
        assertEquals(2, (int)result);
    }

    @Test
    public void testMath() {
        String result = mCalculateExecutor.scienceCalculate("2+3×4",3);
        assertEquals( (Double)14.0, Double.valueOf(result));
    }

    @Test
    public void testMath1() {
        String result = mCalculateExecutor.scienceCalculate("2^3",3);
        assertEquals( (Double) 8.0, Double.valueOf(result));
    }

    @Test
    public void testMath2() {
        String result = mCalculateExecutor.scienceCalculate("2÷3",3);
        assertEquals("0.667", result);
    }
}