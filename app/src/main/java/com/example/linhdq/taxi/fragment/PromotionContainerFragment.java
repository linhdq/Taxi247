package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.adapter.PromotionPagerAdapter;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class PromotionContainerFragment extends Fragment {
    //view
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //
    private Context context;
    //adapter
    private PromotionPagerAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion_container, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        //view
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.promotion_view_pager);
        //
        this.context = view.getContext();
        //adapter
        this.pagerAdapter = new PromotionPagerAdapter(getChildFragmentManager(), this.context);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
