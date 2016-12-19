package com.example.linhdq.taxi.activity;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.constant.Constant;
import com.example.linhdq.taxi.fragment.RegisterFragment;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //start animation
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
        //change title
        changeTitle(getResources().getString(R.string.register));
        //config language
        configLanguage();
        //open default fragment
        openFragment(new RegisterFragment(), false);
    }

    private void configLanguage(){
        SharedPreferences sharedPreferences =
                this.getSharedPreferences(Constant.SHARED_PREFERENCE_KEY,MODE_PRIVATE);
        String languageCode = sharedPreferences.getString(Constant.LANGUAGE_KEY,"vi");
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
    }

    public void changeTitle(String title){
        RegisterActivity.this.setTitle(title);
    }

    public void openFragment(Fragment fragment, boolean addToBackStack) {
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layout_register_container, fragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.toString());
            }
            fragmentTransaction.commit();
        }
    }
}
