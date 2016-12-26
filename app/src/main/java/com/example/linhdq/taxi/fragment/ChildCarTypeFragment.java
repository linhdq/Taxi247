package com.example.linhdq.taxi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.adapter.ListCarTypeAdapter;
import com.example.linhdq.taxi.interf.OnItemSelectedRecyclerView;
import com.example.linhdq.taxi.model.CarTypeModel;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class ChildCarTypeFragment extends Fragment {
    //view
    private RecyclerView recyclerView;
    private ImageView imvArrowLeft;
    private ImageView imvArrowRight;
    //
    private Context context;
    private GridLayoutManager layoutManager;
    //
    private CarTypeModel list[];
    //adapter
    private ListCarTypeAdapter adapter;
    //
    private OnItemSelectedRecyclerView onItemSelectedRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child_car_type, container, false);

        init(view);
        addListener();

        return view;
    }

    private void init(View view) {
        //view
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_car_type);
        imvArrowLeft = (ImageView) view.findViewById(R.id.imv_arrow_left);
        imvArrowRight = (ImageView) view.findViewById(R.id.imv_arrow_right);
        //
        context = view.getContext();
        //config recyclerview
        layoutManager = new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //
        adapter = new ListCarTypeAdapter(context, list, onItemSelectedRecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(list.length / 2);
    }

    private void addListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                calStateArrow();
            }
        });
    }

    private void calStateArrow() {
        imvArrowRight.setVisibility(View.GONE);
        imvArrowLeft.setVisibility(View.GONE);
        if(layoutManager.findFirstVisibleItemPosition()!=0){
            imvArrowLeft.setVisibility(View.VISIBLE);
        }
        if(layoutManager.findLastVisibleItemPosition()!=list.length-1){
            imvArrowRight.setVisibility(View.VISIBLE);
        }
    }

    public void setOnItemSelectedRecyclerView(OnItemSelectedRecyclerView onItemSelectedRecyclerView) {
        this.onItemSelectedRecyclerView = onItemSelectedRecyclerView;
    }

    public void setList(CarTypeModel[] list) {
        this.list = list;
    }
}
