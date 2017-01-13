package com.co.myrappitest;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by JuanCamilo on 12/01/2017.
 */

public class Utils {
    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null) {
            return netInfo.isConnectedOrConnecting();
        }
        return false;
    }
}
