package com.co.myrappitest.GUI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.R;
import com.squareup.picasso.Picasso;

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
        DataT5 cat = getArguments().getParcelable(MainActivity.CATEGORY);
        TextView tx = (TextView) view.findViewById(R.id.title);
        tx.setText(cat.getTitle());
        TextView desc = (TextView) view.findViewById(R.id.description);
        desc.setText(Html.fromHtml(Html.fromHtml(cat.getHtmlDescription()).toString()));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(cat.getTitle());
        ImageView imageView = (ImageView) view.findViewById(R.id.banner_image);
        Picasso.with(view.getContext()).load(cat.getBannerImg()).error(R.mipmap.ic_launcher).into(imageView);
        FrameLayout back = (FrameLayout) view.findViewById(R.id.container_details);
        back.setBackgroundColor(Color.parseColor(cat.getColor()));
        return view;
    }
}
