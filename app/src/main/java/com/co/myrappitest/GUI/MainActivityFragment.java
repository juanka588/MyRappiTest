package com.co.myrappitest.GUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.co.myrappitest.Adapters.DataT5Adapter;
import com.co.myrappitest.DataContent.DataT5Content;
import com.co.myrappitest.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        RecyclerView list = (RecyclerView) view.findViewById(R.id.list_view);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(new DataT5Adapter(DataT5Content.getAllData(view.getContext())));
        return view;
    }
}
