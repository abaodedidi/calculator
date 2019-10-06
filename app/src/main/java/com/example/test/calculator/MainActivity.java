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
import com.example.test.utils.Constant;
import com.example.test.utils.SymbolViewAdapter;

import java.lang.reflect.Method;

public class MainActivity extends Activity implements View.OnClickListener{

    private String DEBUG = "MainActivity";
    private CalculatorEditText mInputView;
    private RecyclerView mSymbolView;
    private SymbolViewAdapter mSymbolAdapter;
    private TextView tv_old;
    private String mInput;
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
    private Button btn_percent;
    private Button btn_reciprocal;

    private Button btn_func_add;
    private Button btn_clear;
    private Button btn_del;
    private Button btn_bracket_left;
    private Button btn_bracket_right;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputView=(CalculatorEditText) findViewById(R.id.et_input);
        mSymbolView=(RecyclerView) findViewById(R.id.symbol_view);
        tv_old=(TextView) findViewById(R.id.tv_old);
        init();
        noPopUpKeyboard();
    }

    private void init() {
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if(mSymbolView!=null){
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

        btn_0=findViewById(R.id.btn_0);
        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
        btn_3=findViewById(R.id.btn_3);
        btn_4=findViewById(R.id.btn_4);
        btn_5=findViewById(R.id.btn_5);
        btn_6=findViewById(R.id.btn_6);
        btn_7=findViewById(R.id.btn_7);
        btn_8=findViewById(R.id.btn_8);
        btn_9=findViewById(R.id.btn_9);
        btn_π=findViewById(R.id.btn_π);
        btn_e=findViewById(R.id.btn_e);
        btn_percent=findViewById(R.id.btn_percent);
        btn_reciprocal=findViewById(R.id.btn_reciprocal);
        btn_func_add=findViewById(R.id.btn_func_add);
        btn_clear=findViewById(R.id.btn_clear);
        btn_del=findViewById(R.id.btn_del);
        btn_bracket_left=findViewById(R.id.btn_bracket_left);
        btn_bracket_right=findViewById(R.id.btn_bracket_right);
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
        btn_percent.setOnClickListener(this);
        btn_reciprocal.setOnClickListener(this);
        btn_func_add.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_bracket_left.setOnClickListener(this);
        btn_bracket_right.setOnClickListener(this);
    }

    SymbolViewAdapter.SymbolListener mSymbolListener=new SymbolViewAdapter.SymbolListener(){

        @Override
        public void onSymbolClick(int index) {

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

    private void LOG(String value){
        Log.d(DEBUG,value);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_0:
                Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_1:
                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            case R.id.btn_6:
                break;
            case R.id.btn_7:
                break;
            case R.id.btn_8:
                break;
            case R.id.btn_9:
                break;
            case R.id.btn_π:
                break;
            case R.id.btn_e:
                break;
            case R.id.btn_percent:
                break;
            case R.id.btn_reciprocal:
                break;
            case R.id.btn_func_add:
                break;
            case R.id.btn_clear:
                break;
            case R.id.btn_del:
                break;
            case R.id.btn_bracket_left:
                break;
            case R.id.btn_bracket_right:
                break;
        }
    }
}
