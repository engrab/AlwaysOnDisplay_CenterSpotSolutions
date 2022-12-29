package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock;


import androidx.annotation.NonNull;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AppOpenManagerAds;
import com.google.android.gms.ads.MobileAds;

public class App extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, initializationStatus -> new AppOpenManagerAds(App.this));

    }


}
