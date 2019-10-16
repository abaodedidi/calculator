package com.example.test.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.ui.CalculateFuncView;
import com.example.test.utils.Constant;
import com.example.test.utils.FunctionManager;

public class FunctionActivity extends Activity {
    private LinearLayout mLinearLayout;
    private CalculateFuncView mCalculateFuncView;
    private TextView mFuncName;
    private ImageView mBackImag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        mLinearLayout = findViewById(R.id.fun_container);
        mFuncName = findViewById(R.id.tv_name);
        mBackImag = findViewById(R.id.iv_back);
        int funcIndex = getIntent().getIntExtra("funcIndex", 0);
        initView(funcIndex);
        mBackImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(int index) {
        switch (index) {
            case Constant.FUNC_TYPE_SIN:
                FunctionManager.getInstance(getApplicationContext()).initSinData();
                setFuncView();
                mFuncName.setText(getString(R.string.sin_name));
                break;
            case Constant.FUNC_TYPE_COS:
                FunctionManager.getInstance(getApplicationContext()).initCosData();
                setFuncView();
                mFuncName.setText(getString(R.string.cos_name));
                break;
            case Constant.FUNC_TYPE_TAN:
                FunctionManager.getInstance(getApplicationContext()).initTanData();
                setFuncView();
                mFuncName.setText(getString(R.string.tan_name));
                break;
            case Constant.FUNC_TYPE_EX:
                FunctionManager.getInstance(getApplicationContext()).initEXData();
                setFuncView();
                mFuncName.setText(getString(R.string.ex_name));
                break;
            case Constant.FUNC_TYPE_SQRT:
                FunctionManager.getInstance(getApplicationContext()).initSqrtData();
                setFuncView();
                mFuncName.setText(getString(R.string.sqrt_name));
                break;
            case Constant.FUNC_TYPE_LOG:
                FunctionManager.getInstance(getApplicationContext()).initLgData();
                setFuncView();
                mFuncName.setText(getString(R.string.log_name));
                break;
            case Constant.FUNC_TYPE_LN:
                FunctionManager.getInstance(getApplicationContext()).initInData();
                setFuncView();
                mFuncName.setText(getString(R.string.ln_name));
                break;
            case Constant.FUNC_TYPE_SQU:
                FunctionManager.getInstance(getApplicationContext()).initSquData();
                setFuncView();
                mFuncName.setText(getString(R.string.squ_name));
                break;
            case Constant.FUNC_TYPE_REC:
                FunctionManager.getInstance(getApplicationContext()).initRecData();
                setFuncView();
                mFuncName.setText(getString(R.string.rec_name));
                break;
        }
    }

    private void setFuncView(){
        mLinearLayout.removeAllViews();
        mCalculateFuncView = new CalculateFuncView(this);
        mCalculateFuncView.setMap(FunctionManager.getInstance(getApplicationContext()).getFunMap());
        mLinearLayout.addView(mCalculateFuncView);
    }


}
