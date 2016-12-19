package com.example.linhdq.taxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //start animation
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
    }
}
