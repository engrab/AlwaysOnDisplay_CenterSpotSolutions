package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.PreviewActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui.MainActivity;

public class AnalogService extends Service {


    String NOTIFICATION_CHANNEL_ID = "1";
    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;
    NotificationChannel mChannel;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ContentValues", "onCreate: ");
        getSharedPreferences("Settings", 0).edit().putBoolean("mainService", true).apply();
        Bitmap decodeResource = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        this.mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        this.mBuilder = builder;

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);

        }

        builder.setContentTitle("Always On Display")
                .setContentText("Always On Display Service Is On.")
                .setTicker("Always running...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setLargeIcon(decodeResource)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setPriority(-1)
                .setVibrate(new long[]{1000})
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setAutoCancel(false);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel2 = new NotificationChannel(this.NOTIFICATION_CHANNEL_ID,
                    "My Notifications",
                    NotificationManager.IMPORTANCE_LOW);
            this.mChannel = notificationChannel2;
            notificationChannel2.setDescription("Channel description");
            this.mChannel.enableLights(true);
            this.mChannel.setLightColor(-16776961);
            this.mChannel.setVibrationPattern(new long[]{1000});
            this.mChannel.enableVibration(true);
            this.mChannel.setLockscreenVisibility(1);
            this.mNotificationManager.createNotificationChannel(this.mChannel);
            this.mBuilder.setChannelId(this.NOTIFICATION_CHANNEL_ID);
            startForeground(1, this.mBuilder.build());
        } else {
            this.mBuilder.setChannelId(this.NOTIFICATION_CHANNEL_ID);
            this.mNotificationManager.notify(1, this.mBuilder.build());
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.mScreenStateBroadcastReceiver, intentFilter);
    }

    private final BroadcastReceiver mScreenStateBroadcastReceiver = new BroadcastReceiver() {
        /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.ClockService.AnonymousClass1 */

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            Log.i("ContentValues", "onReceive: " + intent.getAction());
            if (intent.getAction() != null && intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                new Handler().postDelayed(() -> {
                    try {
                        Log.i("ContentValues", "onReceive: inside " + intent.getAction());
                        Intent intent1 = new Intent(getApplicationContext(), PreviewActivity.class);
                        intent1.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, 500);
            }
        }
    };


    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i("ContentValues", "onCreate: ");
        return START_STICKY;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterReceiver(this.mScreenStateBroadcastReceiver);
        getSharedPreferences("Settings", 0).edit().putBoolean("mainService", false).apply();
        if (Build.VERSION.SDK_INT >= 26) {
            stopForeground(true);
        } else {
            this.mNotificationManager.cancel(1);
        }
        super.onDestroy();
    }
}
