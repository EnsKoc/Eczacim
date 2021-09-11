package com.eneskoc.turkeypharmaciesonduty.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;

import com.eneskoc.turkeypharmaciesonduty.MainActivity;
import com.eneskoc.turkeypharmaciesonduty.R;
import com.eneskoc.turkeypharmaciesonduty.ui.customView.CustomAlertDialog;

public class SplashActivity extends AppCompatActivity implements SplashPresenter.Listener {

    private SplashPresenter splashPresenter;
    private CustomAlertDialog customAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashPresenter = new SplashPresenter(this, SplashActivity.this);
        splashPresenter.isInternetConnection();
    }

    @Override
    public void openMainPage() {
        runOnUiThread(() -> new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        }, 1000));
    }

    @Override
    public void showNetworkErrorDialog() {
        customAlertDialog = new CustomAlertDialog(this);
        customAlertDialog.showAnimation();
        customAlertDialog.setTitle(R.string.no_internet_title);
        customAlertDialog.setDescription(R.string.no_internet_desc);
        customAlertDialog.setPositiveButton(getString(R.string.okay), v -> finish());
        customAlertDialog.setNegativeButton(getString(R.string.open_internet_settings), v -> {
            startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            finish();
        });
        customAlertDialog.show();
    }
}
