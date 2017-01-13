package com.co.myrappitest.GUI;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.co.myrappitest.Adapters.DataT5Adapter;
import com.co.myrappitest.DataContent.DataT5Content;
import com.co.myrappitest.Interfaces.OnCategorySelected;
import com.co.myrappitest.R;
import com.co.myrappitest.Utils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private OnCategorySelected mCallback;
    private TextView networkMessage;

    private final OkHttpClient client = new OkHttpClient();
    private static final String JSON_URL = "https://www.reddit.com/reddits.json";
    private RecyclerView list;
    private boolean twoPanes;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        list = (RecyclerView) view.findViewById(R.id.list_view);
        int span = 1;
        if (twoPanes) {
            span = 2;
        }
        list.setLayoutManager(new GridLayoutManager(getContext(), span));
        list.setAdapter(new DataT5Adapter(DataT5Content.getAllData(view.getContext()), mCallback));
        networkMessage = (TextView) view.findViewById(R.id.connection_message);
        if (Utils.isOnline(getActivity())) {
            if (savedInstanceState == null) {
                parseJSON();
            }
            networkMessage.setVisibility(View.GONE);
        } else {
            networkMessage.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (OnCategorySelected) getActivity();
        twoPanes = getActivity().findViewById(R.id.container_two_panes) != null;
    }

    private void parseJSON() {
        String url = JSON_URL;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                networkMessage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //no internet connection
                    networkMessage.setVisibility(View.VISIBLE);
                } else {
                    try {
                        DataT5Content.updateDataBase(response.body().string(), getContext());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.getAdapter().notifyDataSetChanged();
                            }
                        });
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString());
                    }
                }
            }
        });

    }
}
