package com.testyfood.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by viswas on 8/28/2017.
 */

public class AppCore {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        // return activeNetInfo != null && activeNetInfo.isConnected();
        boolean status = activeNetInfo != null && activeNetInfo.isConnected();
        return status;
    }


}
