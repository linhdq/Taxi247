package com.example.linhdq.taxi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.linhdq.taxi.R;
import com.example.linhdq.taxi.interf.OnItemSelectedRecyclerView;
import com.example.linhdq.taxi.model.CarTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LinhDQ on 12/26/16.
 */

public class ListCarTypeAdapter extends RecyclerView.Adapter<ListCarTypeAdapter.ViewHolder> {
    //
    private Context context;
    private LayoutInflater inflater;
    private CarTypeModel[] listCarType;
    private List<ListCarTypeAdapter.ViewHolder> listItemView;
    private OnItemSelectedRecyclerView listener;

    public ListCarTypeAdapter(Context context, CarTypeModel[] listCarType, OnItemSelectedRecyclerView listener) {
        this.context = context;
        this.listCarType = listCarType;
        this.listener = listener;
        this.inflater = LayoutInflater.from(this.context);
        this.listItemView = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_on_list_car_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CarTypeModel model = listCarType[position];
        if (model != null) {
            holder.imvCar.setImageResource(model.getImage());
            holder.txtCarType.setText(model.getType());
            holder.layoutItem.setAlpha(0.5f);
            holder.itemPos = position;
            listItemView.add(holder);
        }
    }

    @Override
    public int getItemCount() {
        return listCarType.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //view
        private ImageView imvCar;
        private TextView txtCarType;
        private LinearLayout layoutItem;
        private int numberClicked;
        private int itemPos;

        public ViewHolder(View itemView) {
            super(itemView);
            //
            imvCar = (ImageView) itemView.findViewById(R.id.imv_car);
            txtCarType = (TextView) itemView.findViewById(R.id.txt_car_type);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.layout_item_car_type);
            numberClicked = 0;
            itemPos = 0;
            //
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            for (ViewHolder v : listItemView) {
                if (v.itemPos != itemPos) {
                    v.layoutItem.setAlpha(0.5f);
                    v.numberClicked = 0;
                }
            }
            if(itemPos==0){
                listener.OnItemSelected(false);
            }else {
                numberClicked++;
                if (numberClicked == 2) {
                    listener.OnItemSelected(true);
                    numberClicked = 0;
                }
            }
            layoutItem.setAlpha(1.0f);
        }
    }
}
