package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.Variables;

public class ReadNotification extends NotificationListenerService {
    SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences2 = getSharedPreferences("Settings", 0);
        this.mSharedPreferences = sharedPreferences2;
        sharedPreferences2.edit().putBoolean(Variables.IS_NOTIFICATION_ENABLE, true).apply();
    }


    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        super.onNotificationPosted(statusBarNotification);
        this.mSharedPreferences = getSharedPreferences("Settings", 0);
        Intent intent = new Intent("notification_added");
        intent.putExtra("message", statusBarNotification.getPackageName());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }



    @Override
    public void onDestroy() {
        this.mSharedPreferences.edit().putBoolean(Variables.IS_NOTIFICATION_ENABLE, false).apply();
        super.onDestroy();
    }

    @Override
    public void onListenerDisconnected() {
        if (Build.VERSION.SDK_INT >= 24) {
            requestRebind(new ComponentName(this, ReadNotification.class));
        }
    }
}
