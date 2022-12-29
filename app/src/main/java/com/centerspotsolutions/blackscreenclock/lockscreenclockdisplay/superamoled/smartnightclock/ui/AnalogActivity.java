package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.arbelkilani.clock.Clock;
import com.google.android.gms.ads.AdView;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen.AnalogListener;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.services.AnalogService;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ConfigActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.Variables;

public class AnalogActivity extends BaseActivity {

    private static final String TAG = "AnalogClockShow";
    private SharedPreferences mSharedPreference;
    private View analogDigitalTime;
    private TextView tvReminderAnalog;
    private int mClockNum = 1;
    private AdView mBanner;
    private Clock mClock;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_analog);

        tvReminderAnalog = findViewById(R.id.text_view_reminder_analog);
        this.mSharedPreference = getSharedPreferences("Settings", 0);
        this.mClockNum = getIntent().getIntExtra("clockNumber", 1);
        this.mClock = findViewById(R.id.clock_view);

        Log.d(TAG, "onCreate: clock: " + mClockNum);

        View findViewById = findViewById(R.id.linear_layout_analog_digital_time);
        this.analogDigitalTime = findViewById;
        int i = this.mClockNum;

        if (i <= 30 || i >= 43) {
            Log.d(TAG, "onCreate: called");
            findViewById.setVisibility(View.VISIBLE);
        } else {
            findViewById.setVisibility(View.GONE);
        }

        findViewById(R.id.linear_layout_cancel_action).setOnClickListener(view -> finish(view));

        findViewById(R.id.linear_layout_preview).setOnClickListener(view -> displayPreviewActivity(view));

        findViewById(R.id.linear_layout_btn_applyClock).setOnClickListener(view -> {
            enableClock(view);
            AdsUtils.showInterstitial(this);
        });

        LinearLayout linearLayout = findViewById(R.id.linear_layout_theme);
        linearLayout.setVisibility(View.GONE);


        mBanner =AdsUtils.showBanner(this, findViewById(R.id.ad_view_smart));


    }

    @Override
    public void onResume() {
        super.onResume();

        if (mBanner != null) {
            mBanner.resume();
        }

        this.mClock = new ConfigActivity()
                .configureClock(this.mClock,
                        this,
                        this.mClockNum);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBanner != null) {
            mBanner.destroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mBanner != null) {
            mBanner.pause();
        }
    }



    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean isServiceRun(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void enableClock() {
        if (canDraw()) {
            if (!checkIfPermissionIsGrantedAlready()) {
                displayPermissionDialog();
            } else if (!isServiceRun(AnalogService.class)) {
                showDialog();
            } else {
                tvReminderAnalog.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "Clock is Applied .", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkIfPermissionIsGrantedAlready() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == 0;
    }

    private boolean canDraw() {
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            return true;
        }
        displayDialog();
        return false;
    }

    private void displayPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Required");
        builder.setMessage("We need PHONE STATE permission to turn off Always On Display screen in case of a new incoming call.Please Allow it .");
        builder.setCancelable(false);

        builder.setPositiveButton("Allow", (dialogInterface, i) -> {
            dialogInterface.cancel();
            ActivityCompat.requestPermissions(AnalogActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        });

        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
        builder.create().show();
    }

    public void finish(View view) {
        finish();
    }

    public void enableClock(View view) {
        this.mSharedPreference.edit().putInt(Variables.CLOCK_NUMBER, this.mClockNum).apply();
        enableClock();
    }

    private void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Draw over other apps permission needed for Always On Display to work.");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", (dialogInterface, i) -> handleManageOverlayPermissionsDialog(dialogInterface, i));
        builder.setNegativeButton("NO", AnalogListener.INSTANCE);
        builder.create().show();
    }

    public void handleManageOverlayPermissionsDialog(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 0);
    }

    public void displayPreviewActivity(View view) {
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra("clockNum", this.mClockNum);
        startActivity(intent);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enable Smart Clock");
        builder.setMessage("Smart Clock needs permission to run in background. Please Allow it.");
        builder.setCancelable(true);

        builder.setPositiveButton("Turn On", (dialogInterface, i) -> {
            dialogInterface.cancel();
            ContextCompat.startForegroundService(AnalogActivity.this.getApplicationContext(), new Intent(AnalogActivity.this.getApplicationContext(), AnalogService.class));
        });

        builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel());
        builder.create().show();
    }


}