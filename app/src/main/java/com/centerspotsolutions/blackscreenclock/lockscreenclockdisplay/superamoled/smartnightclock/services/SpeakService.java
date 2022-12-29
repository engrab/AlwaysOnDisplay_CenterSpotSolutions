package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.TimeUtil;

import java.util.Calendar;
import java.util.Locale;

public class SpeakService extends Service implements TextToSpeech.OnInitListener {

    private TextToSpeech mTtsObj;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("001", "Channel1", NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.setLockscreenVisibility(0);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
                startForeground(101, new Notification.Builder(getApplicationContext(), "001")
                        .setOngoing(true)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Speaking Clock Time")
                        .build());
            }
        } else {
            startForeground(101, new Notification());
        }
        this.mTtsObj = new TextToSpeech(this, this);
        return START_STICKY;
    }

    @Override
    public void onInit(int i) {
        if (i == 0) {
            int language = this.mTtsObj.setLanguage(Locale.ENGLISH);
            if (language == -1 || language == -2) {
                Log.e("error", "This Language is not supported");
            } else {
                convertTextToSpeech();
            }
            this.mTtsObj.setOnUtteranceProgressListener(new UtteranceProgressListener() {

                @Override
                public void onStart(String str) {
                }

                @Override
                public void onDone(String str) {
                    SpeakService.this.stopSelf();
                }

                @Override
                public void onError(String str) {
                    SpeakService.this.stopSelf();
                }
            });
            return;
        }
        stopSelf();
    }

    private void convertTextToSpeech() {
        Calendar instance = Calendar.getInstance();
        String valueOf = String.valueOf(instance.get(10));
        String valueOf2 = String.valueOf(instance.get(12));
        int i = instance.get(9);
        String GetTimeString = TimeUtil.getTimeString(valueOf, valueOf2, i == 0 ? "AM" : i == 1 ? "PM" : "0");
        this.mTtsObj.speak(GetTimeString, 0, null, "android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mTtsObj.shutdown();
    }
}
