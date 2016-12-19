package com.example.linhdq.taxi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.example.linhdq.taxi.constant.Constant;

import java.util.Locale;

/**
 * Created by LinhDQ on 12/19/16.
 */

public class Application extends android.app.Application {
    //
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //
        context=getApplicationContext();
        //
        languageConfig();
    }

    private void languageConfig(){
        //get language code
        SharedPreferences sharedPreferences =
                context.getSharedPreferences(Constant.SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
        String languageCode = sharedPreferences.getString(Constant.LANGUAGE_KEY,"vi");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.LANGUAGE_KEY,languageCode);
        editor.commit();
        //change language
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());
    }
}
