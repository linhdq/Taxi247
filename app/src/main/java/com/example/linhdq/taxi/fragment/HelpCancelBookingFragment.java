package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.adapter.ChildHelpPagerAdapter;
import com.example.linhdq.taxi.model.ChildHelpModel;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class HelpCancelBookingFragment extends Fragment implements View.OnClickListener {
    //view
    private ViewPager viewPager;
    private TextView btnDismiss;
    private ImageView btnNext;
    private View listTab[];
    //
    private ChildHelpPagerAdapter adapter;
    private int currentPageIndex;
    //
    private Context context;
    //fragment
    private ChildHelpCancelBookingFragment listFragment[];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_cancel_booking, container, false);

        init(view);
        addListener();

        return view;
    }

    private void init(View view) {
        //view
        viewPager = (ViewPager) view.findViewById(R.id.help_cancel_booking_viewpager);
        btnDismiss = (TextView) view.findViewById(R.id.txt_dismiss);
        btnNext = (ImageView) view.findViewById(R.id.imv_arrow_next);
        listTab = new View[3];
        listTab[0] = view.findViewById(R.id.tab_01);
        listTab[1] = view.findViewById(R.id.tab_02);
        listTab[2] = view.findViewById(R.id.tab_03);
        //
        context = view.getContext();
        //fragment
        listFragment = new ChildHelpCancelBookingFragment[3];
        listFragment[0] = (new ChildHelpCancelBookingFragment());
        listFragment[1] = (new ChildHelpCancelBookingFragment());
        listFragment[2] = (new ChildHelpCancelBookingFragment());
        listFragment[0].setModel(new ChildHelpModel((byte) 1, R.drawable.child_help_cancel_booking_pic1,
                getString(R.string.reminder), getString(R.string.help_reminder)));
        listFragment[1].setModel(new ChildHelpModel((byte) 2, R.drawable.child_help_cancel_booking_pic2,
                getString(R.string.your_schedule_booking), getString(R.string.help_your_schedule_booking)));
        listFragment[2].setModel(new ChildHelpModel((byte) 3, R.drawable.child_help_cancel_booking_pic3,
                getString(R.string.cancel_schedule_booking), getString(R.string.help_cancel_schedule_booking)));
        //
        adapter = new ChildHelpPagerAdapter(getChildFragmentManager(), context, listFragment);
        viewPager.setAdapter(adapter);
        currentPageIndex = 0;
        listTab[currentPageIndex].setBackgroundResource(R.drawable.circle_tab_selected);
    }

    private void addListener() {
        btnNext.setOnClickListener(this);
        btnDismiss.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listTab[currentPageIndex].setBackgroundResource(R.drawable.circle_tab_unselected);
                currentPageIndex = position;
                listTab[currentPageIndex].setBackgroundResource(R.drawable.circle_tab_selected);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_dismiss:
                getActivity().onBackPressed();
                break;
            case R.id.imv_arrow_next:
                listTab[currentPageIndex].setBackgroundResource(R.drawable.circle_tab_unselected);
                if (currentPageIndex == 2) {
                    currentPageIndex = 0;
                } else {
                    currentPageIndex++;
                }
                viewPager.setCurrentItem(currentPageIndex);
                listTab[currentPageIndex].setBackgroundResource(R.drawable.circle_tab_selected);
                break;
            default:
                break;
        }
    }
}
