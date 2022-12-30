package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },3000);

    }

}