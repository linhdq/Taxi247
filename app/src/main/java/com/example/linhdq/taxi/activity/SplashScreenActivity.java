package com.example.linhdq.taxi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.constant.Constant;

import java.util.Locale;

/**
 * Created by LinhDQ on 12/19/16.
 */

public class SplashScreenActivity extends AppCompatActivity {
    //time out
    private static int SPLASH_TIME_OUT = 1000;
    //
    private SharedPreferences sharedPreferences;
    //
    private String languageCode;
    private boolean isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //init
        init();
        //config language
        configLanguage();
        //delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (isLogin) {
                    intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                } else {
                    intent = new Intent(SplashScreenActivity.this, RegisterActivity.class);
                }
                //start activity
                SplashScreenActivity.this.startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void init() {
        sharedPreferences = this.getSharedPreferences(Constant.SHARED_PREFERENCE_KEY, MODE_PRIVATE);
        languageCode = sharedPreferences.getString(Constant.LANGUAGE_KEY, "vi");
        isLogin = sharedPreferences.getBoolean(Constant.IS_LOGIN, false);
    }

    private void configLanguage() {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
    }
}
