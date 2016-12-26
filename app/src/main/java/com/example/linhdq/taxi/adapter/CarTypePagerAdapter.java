package com.example.linhdq.taxi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.linhdq.taxi.fragment.ChildCarTypeFragment;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class CarTypePagerAdapter extends FragmentPagerAdapter {
    //
    private ChildCarTypeFragment listFragment[];

    public CarTypePagerAdapter(FragmentManager fm, ChildCarTypeFragment listFragment[]) {
        super(fm);
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
