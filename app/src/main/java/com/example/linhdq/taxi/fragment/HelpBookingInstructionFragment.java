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

public class HelpBookingInstructionFragment extends Fragment implements View.OnClickListener {
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
        View view = inflater.inflate(R.layout.fragment_help_booking_instruction, container, false);

        init(view);
        addListener();

        return view;
    }

    private void init(View view) {
        //view
        viewPager = (ViewPager) view.findViewById(R.id.help_booking_instruction_viewpager);
        btnDismiss = (TextView) view.findViewById(R.id.txt_dismiss);
        btnNext = (ImageView) view.findViewById(R.id.imv_arrow_next);
        listTab = new View[5];
        listTab[0] = view.findViewById(R.id.tab_01);
        listTab[1] = view.findViewById(R.id.tab_02);
        listTab[2] = view.findViewById(R.id.tab_03);
        listTab[3] = view.findViewById(R.id.tab_04);
        listTab[4] = view.findViewById(R.id.tab_05);
        //
        context = view.getContext();
        //fragment
        listFragment = new ChildHelpCancelBookingFragment[5];
        listFragment[0] = (new ChildHelpCancelBookingFragment());
        listFragment[1] = (new ChildHelpCancelBookingFragment());
        listFragment[2] = (new ChildHelpCancelBookingFragment());
        listFragment[3] = (new ChildHelpCancelBookingFragment());
        listFragment[4] = (new ChildHelpCancelBookingFragment());
        listFragment[0].setModel(new ChildHelpModel((byte) 1, R.drawable.child_help_booking_instruction_pic1,
                getString(R.string.car_type_selection), getString(R.string.help_car_type_selection)));
        listFragment[1].setModel(new ChildHelpModel((byte) 2, R.drawable.child_help_booking_instruction_pic2,
                getString(R.string.choose_pickup_drop_off_point), getString(R.string.help_choose_pickup_drop_off_point)));
        listFragment[2].setModel(new ChildHelpModel((byte) 3, R.drawable.child_help_booking_instruction_pic3,
                getString(R.string.booking_confirmation), getString(R.string.help_booking_confirmation)));
        listFragment[3].setModel(new ChildHelpModel((byte) 4, R.drawable.child_help_booking_instruction_pic4,
                getString(R.string.driver_information), getString(R.string.help_driver_information)));
        listFragment[4].setModel(new ChildHelpModel((byte) 5, R.drawable.child_help_booking_instruction_pic5,
                getString(R.string.finish_booking), getString(R.string.help_finish_booking)));
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
                if (currentPageIndex == 4) {
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
