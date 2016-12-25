package com.example.linhdq.taxi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linhdq.taxi.R;

/**
 * Created by LinhDQ on 12/25/16.
 */

public class PromotionFragment extends Fragment {
    //view
    private TextView txtMess;
    //
    private String mess;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotion, container, false);

        init(view);

        return view;
    }

    private void init(View view) {
        //view
        txtMess = (TextView) view.findViewById(R.id.txt_promotion_mess);
        txtMess.setText(mess);
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
