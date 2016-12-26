package com.example.linhdq.taxi.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.fragment.ChildHelpCancelBookingFragment;
import com.example.linhdq.taxi.fragment.PromotionFragment;
import com.example.linhdq.taxi.model.ChildHelpModel;

import java.util.List;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class ChildHelpPagerAdapter extends FragmentPagerAdapter {
    //
    private Context context;
    private ChildHelpCancelBookingFragment listFragment[];

    public ChildHelpPagerAdapter(FragmentManager fm, Context context, ChildHelpCancelBookingFragment listFragment[]) {
        super(fm);
        this.context = context;
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment[position];
    }

    @Override
    public int getCount() {
        return listFragment.length;
    }
}
