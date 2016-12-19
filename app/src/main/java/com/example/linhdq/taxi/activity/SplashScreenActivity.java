package com.example.linhdq.taxi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.linhdq.taxi.R;

/**
 * Created by LinhDQ on 12/19/16.
 */

public class SplashScreenActivity extends AppCompatActivity {
    //time out
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, RegisterActivity.class);
                //start activity
                SplashScreenActivity.this.startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        },SPLASH_TIME_OUT);
    }
}
