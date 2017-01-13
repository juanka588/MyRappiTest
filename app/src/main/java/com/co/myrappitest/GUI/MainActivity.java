package com.co.myrappitest.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.DataContent.DataT5Content;
import com.co.myrappitest.Interfaces.OnCategorySelected;
import com.co.myrappitest.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnCategorySelected {

    private static final String JSONURL = "https://www.reddit.com/reddits.json";
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String CATEGOTY = "categoty";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseJSON();
            }
        });
    }

    public void parseJSON() {
        String url = JSONURL;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //no internet connection
                } else {
                    try {
                        DataT5Content.updateDataBase(response.body().string(), getApplicationContext());
                    } catch (JSONException e) {
                        Log.e(TAG, e.toString());
                    }
                }
            }
        });

    }

    @Override
    public void onCategorySelect(DataT5 object) {
        Intent intent = new Intent(this, AppDetailsActivity.class);
        intent.putExtra(CATEGOTY, object);
        startActivity(intent);
    }
}
