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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen.OverAppsListener;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen.PermissionListener;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen.SettingsListener;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.services.AnalogService;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ads.AdsUtils;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.Variables;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ClockNum;

public class ClocksActivity extends BaseActivity {

    private static final String TAG = "DCPreviewActivity";
    private SharedPreferences mSharedPreferences;
    private TextView tvReminder;
    private FrameLayout previewLayout;
    private View digitalClockLayout;
    private LayoutInflater mInflater;
    private int number = -1;
    private View mApplyClock;
    private View viewCancel;
    private AdView banner;
    private View preview;
    private View theme;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.activity_clocks);
        CreateViews();
        CreateLisners();

        banner = findViewById(R.id.ad_view_smart);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);


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


    private void CreateLisners() {

        this.viewCancel.setOnClickListener(view -> finishActivity(view));

        preview.setOnClickListener(view -> displayPreviewActivity(view));

        mApplyClock.setOnClickListener(view -> {
            enableClock(view);
            AdsUtils.showInterstitial(this);
        });

        theme.setOnClickListener(view -> openAnimationPreviewActivity(view));
    }

    private void displayDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Draw over other apps permission needed for Always On Display to work.");
        builder.setCancelable(true);
        builder.setPositiveButton("YES", (dialogInterface, i) -> managePermissionsOverlay(dialogInterface, i));
        builder.setNegativeButton("NO", OverAppsListener.INSTANCE);
        builder.create().show();
    }


    private boolean isMyServiceRunning(Class<?> cls) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE)) {
            if (cls.getName().equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void enableClock() {
        if (canDrawBool()) {
            if (!checkPermissionAlreadyGiven()) {
                displaypPermissionDialog();
            } else if (!isMyServiceRunning(AnalogService.class)) {
                displayDialogAlwaysOn();
            } else {
                Toast.makeText(getApplicationContext(), "Clock is Applied .", Toast.LENGTH_SHORT).show();
                tvReminder.setVisibility(View.VISIBLE);
            }
        }
    }

    private void displaypPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permission Requird");
        builder.setMessage("We need PHONE STATE permission to turn off Always On Display screen in case of a new incoming call.Please Allow it .");
        builder.setCancelable(false);
        builder.setPositiveButton("Allow", (dialogInterface, i) -> permissionDialogListenerPositive(dialogInterface, i));
        builder.setNegativeButton("No", PermissionListener.INSTANCE);
        builder.create().show();
    }

    public void permissionDialogListenerPositive(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
    }

    public void managePermissionsOverlay(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 0);
    }

    private void displayDialogAlwaysOn() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enable Smart Clock");
        builder.setMessage("Smart Clock needs permission to run in background. Please Allow it.");
        builder.setCancelable(true);
        builder.setPositiveButton("Turn On", (dialogInterface, i) -> {
            dialogInterface.cancel();
            ContextCompat.startForegroundService(getApplicationContext(), new Intent(getApplicationContext(), AnalogService.class));
        });
        builder.setNegativeButton("No", SettingsListener.INSTANCE);
        builder.create().show();
    }

    private boolean checkPermissionAlreadyGiven() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == 0;
    }

    public void finishActivity(View view) {
        finish();
    }

    public void displayPreviewActivity(View view) {
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra("clockNum", this.number);
        startActivity(intent);
    }

    private void CreateViews() {
        tvReminder = findViewById(R.id.text_view_reminder);
        this.number = getIntent().getIntExtra("Digital", 1);
        boolean booleanExtra = getIntent().getBooleanExtra("skipTheme", false);
        this.mSharedPreferences = getSharedPreferences("Settings", 0);
        this.mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        this.previewLayout = findViewById(R.id.previewLayoutframeLayout);
        this.viewCancel = findViewById(R.id.linear_layout_cancel_action);
        View findViewById = findViewById(R.id.linear_layout_theme);
        this.theme = findViewById;
        if (booleanExtra) {
            findViewById.setVisibility(View.GONE);
        } else {
            findViewById.setVisibility(View.VISIBLE);
        }
        this.preview = findViewById(R.id.linear_layout_preview);
        this.mApplyClock = findViewById(R.id.linear_layout_btn_applyClock);
        int i = this.number;

        Log.d(TAG, "initialWork: called i: " + i);

        switch (i) {
            case 31:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock1, null);
                break;
            case 32:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock2, null);
                break;
            case 33:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock3, null);
                break;
            case 34:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock4, null);
                break;
            case 35:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock5, null);
                break;
            case 36:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock6, null);
                break;
            case 37:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock7, null);
                break;
            case 38:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock8, null);
                break;
            case 39:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock9, null);
                break;
            case 40:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock10, null);
                break;
            case 41:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock11, null);
                break;
            case 42:
                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_emoji_clock12, null);
                break;
            default:
                switch (i) {
                    case 101:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital1, null);
                        break;
                    case 102:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital2, null);
                        break;
                    case 103:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital3, null);
                        break;
                    case 104:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital4, null);
                        break;
                    case 105:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital5, null);
                        break;
                    case 106:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital6, null);
                        break;
                    case 107:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital7, null);
                        break;
                    case 108:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital8, null);
                        break;
                    case 109:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital9, null);
                        break;
                    case 110:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital10, null);
                        break;
                    case 111:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital11, null);
                        break;
                    case 112:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital12, null);
                        break;
                    case 113:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital13, null);
                        break;
                    case 114:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital14, null);
                        break;
                    case 115:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital15, null);
                        break;
                    case 116:
                        this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_digital16, null);
                        break;
                    default:
                        switch (i) {
                            case 120:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart1, null);
                                break;
                            case 121:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart2, null);
                                break;
                            case 122:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart3, null);
                                break;
                            case 123:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart4, null);
                                break;
                            case 124:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart5, null);
                                break;
                            case ClockNum.smart6 /* 125 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart6, null);
                                break;
                            case ClockNum.smart7 /* 126 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart7, null);
                                break;
                            case ClockNum.smart8 /* 127 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart8, null);
                                break;
                            case 128:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart9, null);
                                break;
                            case ClockNum.smart10 /* 129 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart10, null);
                                break;
                            case ClockNum.smart11 /* 130 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart11, null);
                                break;
                            case ClockNum.smart12 /* 131 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_smart12, null);
                                break;

                            case ClockNum.Anim9 /* 150 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_9, null);
                                break;
                            case ClockNum.Anim9_1 /* 151 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_9_1, null);
                                break;
                            case ClockNum.Anim9_2 /* 152 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_9_2, null);
                                break;
                            case ClockNum.Anim9_3 /* 153 */:
                                this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_9_3, null);
                                break;

                            default:
                                switch (i) {
                                    case ClockNum.Anim1 /* 136 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_1, null);
                                        break;
                                    case ClockNum.Anim1_1 /* 137 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_1_1, null);
                                        break;
                                    case ClockNum.Anim1_2 /* 138 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_1_2, null);
                                        break;
                                    case ClockNum.Anim1_3 /* 139 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_1_3, null);
                                        break;
                                    case ClockNum.Anim2 /* 140 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_2, null);
                                        break;
                                    case ClockNum.Anim2_1 /* 141 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_2_1, null);
                                        break;
                                    case ClockNum.Anim2_2 /* 142 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_2_2, null);
                                        break;
                                    case ClockNum.Anim2_3 /* 143 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_2_3, null);
                                        break;
                                    case ClockNum.Anim3 /* 144 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_3, null);
                                        break;
                                    case ClockNum.Anim4 /* 145 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_4, null);
                                        break;
                                    case ClockNum.Anim5 /* 146 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_5, null);
                                        break;
                                    case ClockNum.Anim6 /* 147 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_6, null);
                                        break;
                                    case ClockNum.Anim7 /* 148 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_7, null);
                                        break;
                                    case ClockNum.Anim8 /* 149 */:
                                        this.digitalClockLayout = this.mInflater.inflate(R.layout.animated_clock_8, null);
                                        break;
//                                    case Const.Anim9 /* 150 */:
//                                        this.digitalClockLayout = this.inflater.inflate(R.layout.animated_clock_9, (ViewGroup) null);
//                                        break;
//                                    case Const.Anim9_1 /* 151 */:
//                                        this.digitalClockLayout = this.inflater.inflate(R.layout.animated_clock_9_1, (ViewGroup) null);
//                                        break;
//                                    case Const.Anim9_2 /* 152 */:
//                                        this.digitalClockLayout = this.inflater.inflate(R.layout.animated_clock_9_2, (ViewGroup) null);
//                                        break;
//                                    case Const.Anim9_3 /* 153 */:
//                                        this.digitalClockLayout = this.inflater.inflate(R.layout.animated_clock_9_3, (ViewGroup) null);
//                                        break;
                                    default:
                                        switch (i) {
                                            case ClockNum.led1 /* 161 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_1, null);
                                                break;
                                            case ClockNum.led2 /* 162 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_2, null);
                                                break;
                                            case ClockNum.led3 /* 163 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_3, null);
                                                break;
                                            case ClockNum.led4 /* 164 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_4, null);
                                                break;
                                            case ClockNum.led5 /* 165 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_5, null);
                                                break;
                                            case ClockNum.led6 /* 166 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_6, null);
                                                break;
                                            case ClockNum.led7 /* 167 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_7, null);
                                                break;
                                            case ClockNum.led8 /* 168 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_8, null);
                                                break;
                                            case ClockNum.led9 /* 169 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_9, null);
                                                break;
                                            case ClockNum.led10 /* 170 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_10, null);
                                                break;
                                            case ClockNum.led11 /* 171 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_11, null);
                                                break;
                                            case ClockNum.led12 /* 172 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_12, null);
                                                break;
                                            case ClockNum.led13 /* 173 */:
                                                this.digitalClockLayout = this.mInflater.inflate(R.layout.layout_led_clock_2, null);
                                                break;
                                        }
                                }
                        }
                }
        }
        View view = this.digitalClockLayout;
        if (view != null) {
            this.previewLayout.addView(view);
        }
        int i2 = this.number;
        if (i2 >= 161 && i2 <= 173) {
            enableLEDWatch(i2);
        }
        int i3 = this.number;
        if (i3 >= 31 && i3 <= 42) {
            changeBackgroundColor();
        }
        makeWatchMoving(this.number);
    }

    private boolean canDrawBool() {
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            return true;
        }
        displayDialog();
        return false;
    }

    public void enableClock(View view) {
        this.mSharedPreferences.edit().putInt(Variables.CLOCK_NUMBER, this.number).apply();
        enableClock();
    }

    public void openAnimationPreviewActivity(View view) {
        Intent intent = new Intent(this, AnimatedActivity.class);
        intent.putExtra("clockNum", this.number);
        startActivity(intent);
    }


}