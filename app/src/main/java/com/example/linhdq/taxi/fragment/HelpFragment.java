package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.activity.HomeActivity;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class HelpFragment extends Fragment implements View.OnClickListener {
    //view
    private RelativeLayout itemBookingInstruction;
    private RelativeLayout itemCancelBooking;
    //
    private Context context;
    //fragment
    private HelpCancelBookingFragment helpCancelBookingFragment;
    private HelpBookingInstructionFragment helpBookingInstructionFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        init(view);
        addListener();

        return view;
    }

    private void init(View view) {
        //view
        itemBookingInstruction = (RelativeLayout) view.findViewById(R.id.layout_item_booking_instruction);
        itemCancelBooking = (RelativeLayout) view.findViewById(R.id.layout_item_cancel_booking);
        //
        context = view.getContext();
        //fragment
        helpCancelBookingFragment = new HelpCancelBookingFragment();
        helpBookingInstructionFragment = new HelpBookingInstructionFragment();
    }

    private void addListener() {
        itemBookingInstruction.setOnClickListener(this);
        itemCancelBooking.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_item_booking_instruction:
                ((HomeActivity) getActivity()).openFragment(helpBookingInstructionFragment, true, true);
                break;
            case R.id.layout_item_cancel_booking:
                ((HomeActivity) getActivity()).openFragment(helpCancelBookingFragment, true, true);
                break;
            default:
                break;
        }
    }
}
