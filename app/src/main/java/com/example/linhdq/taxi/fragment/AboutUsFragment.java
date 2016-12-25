package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.linhdq.taxi.R;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class AboutUsFragment extends Fragment implements View.OnClickListener {
    //view
    private LinearLayout btnRateApp;
    private LinearLayout btnShare;
    //
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        init(view);
        addListener();

        return view;
    }

    private void init(View view) {
        //view
        btnRateApp = (LinearLayout) view.findViewById(R.id.layout_btn_rate_app);
        btnShare = (LinearLayout) view.findViewById(R.id.layout_btn_share);
        //
        context = view.getContext();
    }

    private void addListener() {
        btnRateApp.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_btn_rate_app:

                break;
            case R.id.layout_btn_share:

                break;
            default:
                break;
        }
    }
}
