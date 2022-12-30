package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock;



import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AppOpenManagerAds;
import com.google.android.gms.ads.MobileAds;
import com.onesignal.OneSignal;

public class App extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId("2dd4edb9-f442-4098-b40d-8562cbe81ed7");

        // promptForPushNotifications will show the native Android notification permission prompt.
        // We recommend removing the following code and instead using an In-App Message to prompt for notification permission (See step 7)
        OneSignal.promptForPushNotifications();
        MobileAds.initialize(this, initializationStatus -> new AppOpenManagerAds(App.this));

    }


}
