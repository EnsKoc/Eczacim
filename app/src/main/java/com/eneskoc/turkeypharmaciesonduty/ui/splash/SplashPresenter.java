package com.eneskoc.turkeypharmaciesonduty.ui.splash;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SplashPresenter {

    private final Listener listener;
    private final Context context;

    public SplashPresenter(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void isInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
            listener.openMainPage();
        else
            listener.showNetworkErrorDialog();
    }

    interface Listener {
        void openMainPage();

        void showNetworkErrorDialog();
    }
}
