package com.example.linhdq.taxi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.fragment.PromotionFragment;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class PromotionPagerAdapter extends FragmentPagerAdapter {
    //
    private final int NUMBER_PAGES = 2;
    //
    private Context context;
    //
    private String tabtile[];
    //fragment
    private PromotionFragment fragmentPromotionCode;
    private PromotionFragment fragmentPromotionNews;

    public PromotionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabtile = new String[]{context.getResources().getString(R.string.promotion_code),
                context.getResources().getString(R.string.promotion_news)};
        this.fragmentPromotionCode = new PromotionFragment();
        this.fragmentPromotionCode.setMess(context.getResources().getString(R.string.promotion_code_mess));
        this.fragmentPromotionNews = new PromotionFragment();
        this.fragmentPromotionNews.setMess(context.getResources().getString(R.string.promotion_news_mess));
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return this.fragmentPromotionCode;
            case 1:
                return this.fragmentPromotionNews;
        }
        return null;
    }

    @Override
    public int getCount() {
        return NUMBER_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtile[position];
    }
}
