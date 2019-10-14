package com.example.test.calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.test.utils.Constant;

public class SplashActivity extends Activity {
    private SharedPreferences mSharedPreferences;
    private boolean isFirstIn=true;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharedPreferences = getSharedPreferences(getString(R.string.pref_settings), MODE_PRIVATE);
        isFirstIn = mSharedPreferences.getBoolean(getString(R.string.pref_isfirstin), Constant.DEFAULT_IS_FIRST_IN);
        if (isFirstIn) {
            Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
        finish();
        SharedPreferences.Editor editor=mSharedPreferences.edit();
        editor.putBoolean(getString(R.string.pref_isfirstin),false);
        editor.commit();
    }
}
