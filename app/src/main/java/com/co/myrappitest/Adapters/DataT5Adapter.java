package com.co.myrappitest.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.R;

import java.util.List;

/**
 * Created by JuanCamilo on 12/01/2017.
 */

public class DataT5Adapter extends RecyclerView.Adapter<DataT5Adapter.T5ViewHolder> {
    private List<DataT5> dataList;

    public DataT5Adapter(List<DataT5> dataList) {
        this.dataList = dataList;
    }

    @Override
    public T5ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_layout, parent, false);
        T5ViewHolder pvh = new T5ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(T5ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class T5ViewHolder extends RecyclerView.ViewHolder {
        public T5ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
