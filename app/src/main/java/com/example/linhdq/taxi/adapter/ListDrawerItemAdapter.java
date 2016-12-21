package com.example.linhdq.taxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.model.ObjectDrawerItem;


/**
 * Created by LinhDQ on 12/20/16.
 */

public class ListDrawerItemAdapter extends BaseAdapter {
    //
    private Context context;
    private LayoutInflater inflater;
    private ObjectDrawerItem list[];

    public ListDrawerItemAdapter(Context context, ObjectDrawerItem[] objects) {
        this.context = context;
        this.list = objects;
        this.inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int i) {
        return list[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_on_list_drawer, parent, false);
        if (view != null) {
            //init view
            ImageView imvIcon = (ImageView) view.findViewById(R.id.imv_item_icon);
            TextView txtName = (TextView) view.findViewById(R.id.txt_item_detail);
            //fill data
            ObjectDrawerItem obj = list[position];
            imvIcon.setImageResource(obj.getIcon());
            txtName.setText(obj.getName());
            if (obj.isSelected()) {
                (view.findViewById(R.id.item_view)).setBackgroundColor(
                        context.getResources().getColor(R.color.orange_800));
            } else {
                (view.findViewById(R.id.item_view)).setBackgroundColor(
                        context.getResources().getColor(R.color.transparent_100));
            }
        }
        return view;
    }
}