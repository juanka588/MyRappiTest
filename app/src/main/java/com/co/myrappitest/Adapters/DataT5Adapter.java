package com.co.myrappitest.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.Interfaces.OnCategorySelected;
import com.co.myrappitest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by JuanCamilo on 12/01/2017.
 */

public class DataT5Adapter extends RecyclerView.Adapter<DataT5Adapter.T5ViewHolder> {
    private List<DataT5> dataList;
    private OnCategorySelected mCallback;

    public DataT5Adapter(List<DataT5> dataList, OnCategorySelected mCallback) {
        this.dataList = dataList;
        this.mCallback = mCallback;
    }

    @Override
    public T5ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);
        T5ViewHolder pvh = new T5ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(T5ViewHolder holder, int position) {
        DataT5 dataT5 = dataList.get(position);
        holder.title.setText(dataT5.getTitle());
        holder.title.setBackgroundColor(Color.parseColor(dataT5.getColor()));
        Picasso.with(holder.context).load(dataT5.getHeaderImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private void selectItem(int position) {
        mCallback.onCategorySelect(dataList.get(position));
    }

    public class T5ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView imageView;
        Context context;
        CardView container;

        public T5ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            title = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.banner_image);
            container = (CardView) itemView.findViewById(R.id.card_view);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectItem(getAdapterPosition());
                }
            });
        }
    }


}
