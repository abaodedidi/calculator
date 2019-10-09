package com.example.test.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.test.calculator.R;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Arithmetic calculation class
 */
public class CalculateExecutor {

    //operational symbol map
    private final Map<Character, Integer> operationMap = new HashMap<Character, Integer>() {{
        put('+', 0);
        put('-', 1);
        put('×', 2);
        put('÷', 3);
        put('(', 4);
        put(')', 5);
        put('#', 6);
    }};

    //operator priority two-dimensional array
    private final char[][] operationPriority = {
            {'>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '<', '<', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '>', '>'},
            {'>', '>', '>', '>', '<', '>', '>'},
            {'<', '<', '<', '<', '<', '=', ' '},
            {'>', '>', '>', '>', ' ', '>', '>'},
            {'<', '<', '<', '<', '<', ' ', '='},
    };

    private Context mContext;

    public CalculateExecutor(Context context){
        this.mContext = context;
    }

    //science calculate
    public String scienceCalculate(String mathStr, int percision) {
        String resultStr = "";
        if (!checkMathStrAvailable(mathStr)) {
            Utils.showToast(mContext, mContext.getString(R.string.math_error));
            return "Error";
        }

        mathStr = mathStr.replace(" ", "");
        mathStr = mathStr.replace("π", String.valueOf(Math.PI));
        mathStr = mathStr.replace("e", String.valueOf(Math.exp(1)));

        if (mathStr.contains("^")) {
            mathStr = calculateIndex(mathStr);
        }

        if (containFunc(mathStr)) {
            mathStr = calculateFunc(mathStr);
        }

        double result = calculateMath(mathStr);
        if (result == Double.MAX_VALUE)
            resultStr = "Error";
        else {
            BigDecimal b = new BigDecimal(result);
            resultStr = String.valueOf(b.setScale(percision, BigDecimal.ROUND_HALF_UP).doubleValue()); //rounding to reserve the corresponding decimal number
        }

        return resultStr;
    }

    //basic operations
    public double baseCalculate(double x, char o, double y) {
        double result = 0;
        switch (o) {
            case '+':
                result = x + y;
                break;
            case '-':
                result = x - y;
                break;
            case '×':
                result = x * y;
                break;
            case '÷':
                if (y == 0) {
                    Toast.makeText(mContext, mContext.getString(R.string.div_zero), Toast.LENGTH_SHORT).show();
                } else {
                    result = x / y;
                }
                break;
            default:
                break;

        }
        return result;
    }

    //check the validity of input data
    private boolean checkMathStrAvailable(String mathStr) {
        int leftBracketNum = 0;
        int rightBracketNum = 0;
        //check whether brackets are legitimate
        if (mathStr.contains("(") || mathStr.contains(")")) {
            for (int i = 0; i < mathStr.length(); i++) {
                if (mathStr.charAt(i) == '(') {
                    leftBracketNum++;
                } else if (mathStr.charAt(i) == ')') {
                    rightBracketNum++;
                }
            }
            if (leftBracketNum != rightBracketNum) {
                return false;
            }
        }
        //check the beginning and end of the formula
//        char start=mathStr.charAt(0);
        char end = mathStr.charAt(mathStr.length() - 1);
        if (!(Utils.isNum(end + "") || Utils.isNoNumAfter(end))) {
            return false;
        }
        return true;
    }

    //calculate the result of the exponential operation
    private String calculateIndex(String math) {
        String mathStr = math;
        while (mathStr.contains("^")) {
            int lastIndex = mathStr.lastIndexOf("^");
            String left = mathStr.substring(0, lastIndex);
            String right = mathStr.substring(lastIndex + 1, mathStr.length());
            double leftNumResult = 0;
            double rightNumResult = 0;
            String leftNumStr="";
            String rightNumStr="";
            if (left.charAt(left.length() - 1) == ')') {
                int index = left.length() - 2;
                while (index >= 0 && left.charAt(index) != '(') {
                    index--;
                }
                String leftMath = left.substring(index + 1, left.length());
                leftNumResult = calculateMath(leftMath);
                mathStr=mathStr.replace("(" + leftMath + ")", String.valueOf(leftNumResult));
                leftNumStr=leftNumResult+"";
            } else {
                int index = left.length() - 1;
                while (index >= 0 && !Utils.isOpera(left.charAt(index))) {
                    index--;
                }
                leftNumStr=left.substring(index + 1, left.length());
                leftNumResult = Double.parseDouble(leftNumStr);
            }

            if (right.charAt(0) == '(') {
                int index = 1;
                while (index <= (right.length() - 1) && right.charAt(index) != ')') {
                    index++;
                }
                String rightMath = right.substring(index - 1, right.length()-1);
                rightNumResult = calculateMath(rightMath);
                mathStr=mathStr.replace("(" + rightMath + ")", String.valueOf(rightNumResult));
                rightNumStr=rightNumResult+"";
            } else {
                int index = 0;
                while (index <= (right.length() - 1) && !Utils.isOpera(right.charAt(index))) {
                    index++;
                }
                rightNumStr=right.substring(0, index);
                rightNumResult = Double.parseDouble(rightNumStr);
            }

            double result = Math.pow(leftNumResult, rightNumResult);
            if (result > Double.MAX_VALUE) {
                return String.valueOf(Double.MAX_VALUE);
            } else {
                mathStr=mathStr.replace(leftNumStr + "^" + rightNumStr, result + "");
            }
        }
        return mathStr;
    }

    //calculate the result of the operation of the function
    private String calculateFunc(String math) {
        String mathStr = math;
        while (containFunc(mathStr)) {
            int startIndex = mathStr.lastIndexOf("(");
            int endIndex = getFirstRightBracketIndex(mathStr, startIndex);
            String subMath = math.substring(startIndex + 1, endIndex);
            double subResult = calculateMath(subMath);
            if (subResult == Double.MAX_VALUE)
                return String.valueOf(Double.MAX_VALUE);

            int i = startIndex - 1;
            while (i >= 0 && !Utils.isOpera(mathStr.charAt(i))) {
                i--;
            }
            String FuncSymbol = mathStr.substring(i + 1, startIndex);

            String tempMathStr;
            double tempResult;
            switch (FuncSymbol) {
                case "sin":
                    tempMathStr = "sin(" + subMath + ")";
                    tempResult = Math.sin(subResult);
                    mathStr = mathStr.replace(tempMathStr, String.valueOf(tempResult));
                    break;
                case "cos":
                    tempMathStr = "cos(" + subMath + ")";
                    tempResult = Math.cos(subResult);
                    mathStr = mathStr.replace(tempMathStr, String.valueOf(tempResult));
                    break;
                case "tan":
                    tempMathStr = "tan(" + subMath + ")";
                    tempResult = Math.tan(subResult);
                    mathStr = mathStr.replace(tempMathStr, String.valueOf(tempResult));
                    break;
                case "ln":
                    tempMathStr = "ln(" + subMath + ")";
                    tempResult = Math.log(subResult);
                    mathStr = mathStr.replace(tempMathStr, String.valueOf(tempResult));
                    break;
                case "log":
                    tempMathStr = "log(" + subMath + ")";
                    tempResult = Math.log10(subResult);
                    mathStr = mathStr.replace(tempMathStr, String.valueOf(tempResult));
                    break;
                case "√":
                    tempMathStr = "√(" + subMath + ")";
                    tempResult = Math.sqrt(subResult);
                    mathStr = mathStr.replace(tempMathStr, String.valueOf(tempResult));
                    break;
                default:
                    break;
            }
        }

        return mathStr;
    }


    private double calculateMath(String math) {
        if (math.length() == 0) {
            return Double.MAX_VALUE;
        } else {
            if (Utils.isRealNum(math.substring(1, math.length())) || math.contains("E-")) {
                return Double.parseDouble(math);
            } else {
                return NormalCalculateMath(math);
            }
        }
    }

    //ordinary arithmetic calculation
    private double NormalCalculateMath(String math) {
        if (math.length() == 0) {
            return Double.MAX_VALUE;
        } else {
            if (Utils.isRealNum(math.substring(1, math.length())) || math.contains("E-")) {
                return Double.parseDouble(math);
            }

            int flag = 0;
            if (math.charAt(0) == '-') {
                flag = 1;
                math = math.substring(1, math.length());
            }

            Stack<Character> operStack = new Stack<>();
            Stack<Double> numStack = new Stack<>();

            operStack.push('#');
            math += "#";

            String tempNum = "";

            for (int i = 0; i < math.length(); i++) {

                char charOfMath = math.charAt(i);

                if (!Utils.isExtrOpera(charOfMath)
                        || charOfMath == '-' && math.charAt(i - 1) == '(') {
                    tempNum += charOfMath;

                    i++;
                    charOfMath = math.charAt(i);

                    if (Utils.isExtrOpera(charOfMath)) {
                        double num = Double.parseDouble(tempNum);
                        if (flag == 1) {
                            num = -num;
                            flag = 0;
                        }
                        numStack.push(num);
                        tempNum = "";
                    }

                    i--;
                }

                else {

                    switch (getPrior(operStack.peek(), charOfMath)) {

                        case '<':
                            operStack.push(charOfMath);
                            break;

                        case '=':
                            operStack.pop();
                            break;

                        case '>':
                            char oper = operStack.pop();
                            double b = numStack.pop();
                            double a = numStack.pop();
                            if (baseCalculate(a, oper, b) == Double.MAX_VALUE)
                                return Double.MAX_VALUE;
                            numStack.push(baseCalculate(a, oper, b));
                            i--;
                            break;
                    }
                }
            }
            return numStack.peek();
        }
    }

    private boolean containFunc(String mathStr) {
        return mathStr.contains("sin") || mathStr.contains("cos") || mathStr.contains("tan") || mathStr.contains("√") || mathStr.contains("log")
                || mathStr.contains("ln");
    }

    private char getPrior(char oper1, char oper2) {
        return operationPriority[operationMap.get(oper1)][operationMap.get(oper2)];
    }

    //get the right bracket index
    private int getFirstRightBracketIndex(String mathStr, int startIndex) {
        if (startIndex >= mathStr.length()) {
            return 0;
        }
        for (int i = startIndex; i < mathStr.length(); i++) {
            if (mathStr.charAt(i) == ')') {
                return i;
            }
        }
        return 0;
    }
}
