package com.co.myrappitest.GUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AppDetailsActivityFragment extends Fragment {

    public AppDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_app_details, container, false);
        DataT5 cat = getArguments().getParcelable(MainActivity.CATEGOTY);
        TextView tx = (TextView) view.findViewById(R.id.title);
        tx.setText(cat.getTitle());
        TextView desc = (TextView) view.findViewById(R.id.description);
        desc.setText(Html.fromHtml(Html.fromHtml(cat.getHtmlDescription()).toString()));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(cat.getTitle());
        return view;
    }
}
