package com.co.myrappitest.GUI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.co.myrappitest.Data.DataT5;
import com.co.myrappitest.Interfaces.OnCategorySelected;
import com.co.myrappitest.R;

public class MainActivity extends AppCompatActivity implements OnCategorySelected {

    public static final String CATEGORY = "category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new MainActivityFragment());
        fragmentTransaction.commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onCategorySelect(DataT5 object) {
        Bundle b = new Bundle();
        b.putParcelable(CATEGORY, object);
        if (findViewById(R.id.container) != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Fragment fragment = new AppDetailsActivityFragment();
            fragment.setArguments(b);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            Intent intent = new Intent(this, AppDetailsActivity.class);
            intent.putExtras(b);
            Pair<View, String> p1 = Pair.create(findViewById(R.id.banner_image), "image_change");
            Pair<View, String> p2 = Pair.create(findViewById(R.id.banner_image), "title_change");
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, p1, p2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
