package com.example.linhdq.taxi.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.adapter.ListDrawerItemAdapter;
import com.example.linhdq.taxi.constant.Constant;
import com.example.linhdq.taxi.fragment.FeedbackFragment;
import com.example.linhdq.taxi.model.ObjectDrawerItem;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    //view
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private RelativeLayout layoutDrawer;
    private RelativeLayout layoutVietnam;
    private RelativeLayout layoutEnglish;
    private ImageView imvCheckVietnam;
    private ImageView imvCheckEnglish;
    //
    private ObjectDrawerItem listItem[];
    //adapter
    private ListDrawerItemAdapter itemAdapter;
    //
    private SharedPreferences sharedPreferences;
    //
    private String languageCode;
    private byte itemIndex;
    //fragment
    private FeedbackFragment feedbackFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.taxi_booking));
        //
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //init
        init();
        addListener();
        //config language
        configLanguage();
        //start animation
        overridePendingTransition(R.anim.trans_in, R.anim.trans_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //check language status
        checkStatusLanguage();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void init() {
        //view
        drawerList = (ListView) findViewById(R.id.list_item_drawer);
        layoutDrawer = (RelativeLayout) findViewById(R.id.left_drawer);
        layoutVietnam = (RelativeLayout) findViewById(R.id.layout_vietnam_flag);
        layoutEnglish = (RelativeLayout) findViewById(R.id.layout_english_flag);
        imvCheckVietnam = (ImageView) findViewById(R.id.imv_check_vietnam);
        imvCheckEnglish = (ImageView) findViewById(R.id.imv_check_english);
        //
        listItem = new ObjectDrawerItem[7];
        listItem[0] = new ObjectDrawerItem(R.drawable.ic_car, getResources().getString(R.string.taxi_booking), true);
        listItem[1] = new ObjectDrawerItem(R.drawable.ic_history, getResources().getString(R.string.history), false);
        listItem[2] = new ObjectDrawerItem(R.drawable.ic_price_tag, getResources().getString(R.string.promotion), false);
        listItem[3] = new ObjectDrawerItem(R.drawable.ic_help, getResources().getString(R.string.helps), false);
        listItem[4] = new ObjectDrawerItem(R.drawable.ic_mail, getResources().getString(R.string.feedback), false);
        listItem[5] = new ObjectDrawerItem(R.drawable.ic_about_us, getResources().getString(R.string.about_us), false);
        listItem[6] = new ObjectDrawerItem(R.drawable.ic_logout, getResources().getString(R.string.logout), false);
        //
        itemIndex = 0;
        //adapter
        itemAdapter = new ListDrawerItemAdapter(this, listItem);
        drawerList.setAdapter(itemAdapter);
        //
        sharedPreferences = this.getSharedPreferences(Constant.SHARED_PREFERENCE_KEY, MODE_PRIVATE);
        languageCode = sharedPreferences.getString(Constant.LANGUAGE_KEY, "vi");
        //fragment
        feedbackFragment = new FeedbackFragment();
    }

    private void addListener() {
        layoutVietnam.setOnClickListener(this);
        layoutEnglish.setOnClickListener(this);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listItem[itemIndex].setSelected(false);
                itemIndex= (byte) i;
                listItem[itemIndex].setSelected(true);
                itemAdapter.notifyDataSetChanged();
                setTitle(listItem[i].getName());
                drawerLayout.closeDrawer(layoutDrawer);
                switch (i) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        openFragment(feedbackFragment, false);
                        break;
                    case 5:

                        break;
                    case 6:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(Constant.IS_LOGIN, false);
                        editor.commit();
                        refreshActivity();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void configLanguage() {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());
    }

    private void changeLanguage() {
        //save to shared preference
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.LANGUAGE_KEY, languageCode);
        editor.commit();
        //refresh
        refreshActivity();
    }

    private void refreshActivity() {
        Intent intent = new Intent(HomeActivity.this, SplashScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        HomeActivity.this.finish();
    }

    private void checkStatusLanguage() {
        if (languageCode.equalsIgnoreCase("vi")) {
            imvCheckVietnam.setVisibility(View.VISIBLE);
            imvCheckEnglish.setVisibility(View.GONE);
            layoutVietnam.setEnabled(false);
            layoutEnglish.setEnabled(true);
        } else {
            imvCheckVietnam.setVisibility(View.GONE);
            imvCheckEnglish.setVisibility(View.VISIBLE);
            layoutVietnam.setEnabled(true);
            layoutEnglish.setEnabled(false);
        }
    }

    public void openFragment(Fragment fragment, boolean addToBackStack) {
        if (!fragment.isAdded()) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.setCustomAnimations(R.anim.trans_in, R.anim.trans_out,
//                    R.anim.trans_back_in, R.anim.trans_back_out);
            fragmentTransaction.replace(R.id.layout_home_container, fragment);
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(fragment.toString());
            }
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_vietnam_flag:
                languageCode = "vi";
                changeLanguage();
                break;
            case R.id.layout_english_flag:
                languageCode = "en";
                changeLanguage();
                break;
            default:
                break;
        }
    }
}
