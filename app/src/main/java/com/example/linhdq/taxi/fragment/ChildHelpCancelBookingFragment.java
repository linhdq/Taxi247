package com.example.linhdq.taxi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.model.ChildHelpModel;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class ChildHelpCancelBookingFragment extends Fragment {
    //View
    private ImageView imvImage;
    private TextView txtIndex;
    private TextView txtTitle;
    private TextView txtContent;
    //model
    private ChildHelpModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_help, container, false);

        init(view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //
        fillData();
    }

    private void init(View view){
        //view
        imvImage=(ImageView) view.findViewById(R.id.imv_image);
        txtIndex=(TextView) view.findViewById(R.id.txt_index);
        txtTitle=(TextView) view.findViewById(R.id.txt_title);
        txtContent=(TextView) view.findViewById(R.id.txt_content);
    }

    private void fillData(){
        if(model!=null){
            imvImage.setImageResource(model.getImage());
            txtTitle.setText(model.getTitle());
            txtIndex.setText(String.valueOf(model.getIndex()));
            txtContent.setText(model.getContent());
        }
    }

    public void setModel(ChildHelpModel model) {
        this.model = model;
    }
}
