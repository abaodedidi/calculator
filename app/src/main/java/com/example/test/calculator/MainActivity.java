package com.example.test.calculator;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.ui.CalculatorEditText;
import com.example.test.utils.CalculateExecutor;
import com.example.test.utils.Constant;
import com.example.test.utils.SymbolViewAdapter;
import com.example.test.utils.Utils;

import java.lang.reflect.Method;

public class MainActivity extends Activity implements View.OnClickListener {

    private String DEBUG = "MainActivity";
    private CalculatorEditText mInputView;
    private RecyclerView mSymbolView;
    private SymbolViewAdapter mSymbolAdapter;
    private CalculateExecutor calculateExecutor;
    private TextView tv_old;
    private String mInput;
    
    private boolean isClickEqual;
    private String mOldInput;

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;

    private Button btn_π;
    private Button btn_e;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_mul;
    private Button btn_div;
    private Button btn_square;
    private Button btn_reciprocal;
    private Button btn_equal;

    private Button btn_func_add;
    private Button btn_clear;
    private Button btn_del;
    private Button btn_bracket_left;
    private Button btn_bracket_right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputView = (CalculatorEditText) findViewById(R.id.et_input);
        mSymbolView = (RecyclerView) findViewById(R.id.symbol_view);
        tv_old = (TextView) findViewById(R.id.tv_old);
        init();
        noPopUpKeyboard();
    }

    private void init() {
        mInput = "";
        mOldInput = "";
        calculateExecutor = new CalculateExecutor(getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (mSymbolView != null) {
            mSymbolView.setLayoutManager(manager);
            mSymbolView.addItemDecoration(new SymbolViewAdapter.SymbolDecoration());
        }
        if (mSymbolAdapter == null) {
            mSymbolAdapter = new SymbolViewAdapter(getApplicationContext());
        }
        mSymbolAdapter.setSymbolList(Constant.getSymbols());
        mSymbolAdapter.setSymbolListener(mSymbolListener);
        mSymbolView.setAdapter(mSymbolAdapter);
        mSymbolAdapter.notifyDataSetChanged();

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_π = findViewById(R.id.btn_π);
        btn_e = findViewById(R.id.btn_e);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);
        btn_square = findViewById(R.id.btn_square);
        btn_reciprocal = findViewById(R.id.btn_reciprocal);
        btn_func_add = findViewById(R.id.btn_func_add);
        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_del);
        btn_bracket_left = findViewById(R.id.btn_bracket_left);
        btn_bracket_right = findViewById(R.id.btn_bracket_right);
        btn_equal = findViewById(R.id.btn_equal);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_π.setOnClickListener(this);
        btn_e.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_square.setOnClickListener(this);
        btn_reciprocal.setOnClickListener(this);
        btn_func_add.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_bracket_left.setOnClickListener(this);
        btn_bracket_right.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    SymbolViewAdapter.SymbolListener mSymbolListener = new SymbolViewAdapter.SymbolListener() {

        @Override
        public void onSymbolClick(int index) {
            if (mInput.equals("Error")) {
                mInput = "0";
            }
            if (isClickEqual) {
                mInput = "";
                isClickEqual = false;
            }
            switch (index) {
                case 0:
                    setFunInputView("sin(");
                    break;
                case 1:
                    setFunInputView("cos(");
                    break;
                case 2:
                    setFunInputView("tan(");
                    break;
                case 3:
                    setFunInputView("e^(");
                    break;
                case 4:
                    setIndexInputView("^(");
                    break;
                case 5:
                    setFunInputView("√(");
                    break;
                case 6:
                    setFunInputView("log(");
                    break;
                case 7:
                    setFunInputView("ln(");
                    break;
            }
        }
    };


    //禁止弹出键盘
    private void noPopUpKeyboard() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mInputView.setShowSoftInputOnFocus(false);
        } else {
            try {
                Method method = EditText.class.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(this, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mInput.equals("Error")) {
            mInput = "0";
        }
        if (v.getId() != R.id.btn_equal && isClickEqual) {
            mInput = "";
            isClickEqual = false;
        }
        switch (v.getId()) {
            case R.id.btn_0:
                if (mInput.length() == 0) {
                    mInput = "0";
                } else if (mInput.length() == 1) {
                    if (Utils.isNum(mInput)) {
                        if (mInput.equals("0")) {
                            mInput = "0";
                        } else {
                            mInput += "0";
                        }
                    } else if (mInput.equals("+") || mInput.equals("-")) {
                        mInput += "0";
                    } else {
                        Utils.showToast(getApplicationContext(), getString(R.string.input_wrong));
                    }
                } else {
                    mInput += "0";
                }
                mInputView.setText(mInput);
                break;
            case R.id.btn_1:
                setNumInputView("1");
                break;
            case R.id.btn_2:
                setNumInputView("2");
                break;
            case R.id.btn_3:
                setNumInputView("3");
                break;
            case R.id.btn_4:
                setNumInputView("4");
                break;
            case R.id.btn_5:
                setNumInputView("5");
                break;
            case R.id.btn_6:
                setNumInputView("6");
                break;
            case R.id.btn_7:
                setNumInputView("7");
                break;
            case R.id.btn_8:
                setNumInputView("8");
                break;
            case R.id.btn_9:
                setNumInputView("9");
                break;
            case R.id.btn_add:
                setOperaInputView("+");
                break;
            case R.id.btn_sub:
                setOperaInputView("-");
                break;
            case R.id.btn_mul:
                setOperaInputView("×");
                break;
            case R.id.btn_div:
                setOperaInputView("÷");
                break;
            case R.id.btn_π:
                setFunInputView("π");
                break;
            case R.id.btn_e:
                setFunInputView("e");
                break;
            case R.id.btn_square:
                setIndexInputView("^2");
                break;
            case R.id.btn_reciprocal:
                setFunInputView("(1÷");
                break;
            case R.id.btn_func_add:
                break;
            case R.id.btn_clear:
                mInput = "0";
                mInputView.setText(mInput);
                break;
            case R.id.btn_del:
                if (mInput.length() == 0) {
                    mInput = "0";
                } else {
                    mInput = mInput.substring(0, mInput.length() - 1);
                }
                if (mInput.equals("")) {
                    mInput = "0";
                }
                mInputView.setText(mInput);
                break;
            case R.id.btn_bracket_left:
                if (mInput.length() == 0 || (mInput.length() == 1 && mInput.equals("0"))) {
                    mInput = "(";
                } else {
                    char lastInput = getLastInput();
                    if (Utils.isNum(lastInput + "") || Utils.isNoNumAfter(lastInput) || ")".equals(lastInput + "")) {
                        mInput += "×(";
                    } else {
                        mInput += "(";
                    }
                }
                mInputView.setText(mInput);
                break;
            case R.id.btn_bracket_right:
                if (mInput.length() == 0 || (mInput.length() == 1 && mInput.equals("0"))) {
                    mInput = ")";
                } else {
                    if (Utils.isOpera(mInput.charAt(mInput.length() - 1))) {
                        Utils.showToast(getApplicationContext(), getString(R.string.input_wrong));
                    } else {
                        mInput += ")";
                    }
                }
                mInputView.setText(mInput);
                break;
            case R.id.btn_equal:
                isClickEqual=true;
                String result = calculateExecutor.scienceCalculate(mInput);
                mInput = result;
                if (mInput.charAt(mInput.length() - 2) == '.' && mInput.charAt(mInput.length() - 1) == '0') {
                    mInput = mInput.substring(0, mInput.length() - 2);
                }
                mInputView.setText(mInput);
                break;
        }
    }

    private void setOperaInputView(String value) {
        if (mInput.length() == 0) {
            return;
        } else {
            char lastInput = getLastInput();
            if (Utils.isNum(lastInput + "") || lastInput == ')' || lastInput == 'e' || lastInput == 'π') {
                mInput += value;
            }
        }
        mInputView.setText(mInput);
    }

    private void setIndexInputView(String value) {
        if (mInput.length() == 0) {
            return;
        } else if (Utils.isNum(getLastInput() + "") || ")".equals(getLastInput() + "")) {
            mInput += value;
        }
        mInputView.setText(mInput);
    }

    private void setNumInputView(String value) {
        if (mInput.length() == 0) {
            mInput = value;
        } else if (mInput.length() == 1) {
            if (Utils.isNum(mInput)) {
                if (mInput.equals("0")) {
                    mInput = value;
                } else {
                    mInput += value;
                }
            } else if (mInput.equals("+") || mInput.equals("-") || mInput.equals("(")) {
                mInput += value;
            } else {
                if (Utils.isNoNumAfter(mInput.charAt(0))) {
                    mInput = mInput + "×" + value;
                } else {
                    Utils.showToast(getApplicationContext(), getString(R.string.input_wrong));
                }
            }
        } else {
            if (Utils.isNoNumAfter(getLastInput())) {
                mInput = mInput + "×" + value;
            } else {
                mInput += value;
            }
        }
        mInputView.setText(mInput);
    }

    private void setFunInputView(String value) {
        if (mInput.length() == 0 || (mInput.length() == 1 && mInput.equals("0"))) {
            mInput = value;
        } else {
            char lastInput = getLastInput();
            if (Utils.isOpera(lastInput) || lastInput == '(') {
                mInput += value;
            }
        }
        mInputView.setText(mInput);
    }

    private char getLastInput() {
        return mInput.charAt(mInput.length() - 1);
    }


    private void LOG(String value) {
        Log.d(DEBUG, value);
    }
}
