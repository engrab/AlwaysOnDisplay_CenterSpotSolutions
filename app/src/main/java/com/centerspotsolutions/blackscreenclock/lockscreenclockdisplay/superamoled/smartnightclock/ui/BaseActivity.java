package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;

import androidx.appcompat.app.AppCompatActivity;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ClockNum;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.CustomAnalogClockView;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.SharedPref;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Timer;
import java.util.TimerTask;

import at.grabner.circleprogress.CircleProgressView;

public class BaseActivity extends AppCompatActivity {

    CircularProgressBar minProgressBar;
    CircleProgressView minSegmentProgress;
    Time now;
    CircularProgressBar secProgressBar;
    CircleProgressView secSegmentProgress;
    SharedPref sharedPref;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sharedPref = new SharedPref(getApplicationContext());
    }


    public void changeBackgroundColor() {
        findViewById(R.id.ChangeBG).setBackgroundColor(Color.parseColor("#000000"));
    }

    public void updateSecondProgress() {
        this.secProgressBar = findViewById(R.id.seconds_progress_bar);
        new Timer().schedule(new TimerTask() {

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                // java.lang.Runnable
                runOnUiThread(() -> runThread());
            }

            public void runThread() {
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                secProgressBar.setProgress((float) now.second);
            }
        }, 0, 1000);
    }

    public void updateMinutesProgress() {
        this.minProgressBar = findViewById(R.id.minutes_progress_bar);
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> runThread());
            }

            public void runThread() {
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                minProgressBar.setProgress((float) now.minute);
            }
        }, 0, 1000);
    }

    public void updateSegmentSecondProgress() {
        this.secSegmentProgress = findViewById(R.id.SecSegmentProgress);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> runThread());
            }

            public void runThread() {
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                secSegmentProgress.setValue((float) now.second);
            }
        }, 0, 1000);
    }

    public void updateSegmentMinuteSecondProgress() {
        this.secSegmentProgress = findViewById(R.id.SecSegmentProgress);
        this.minSegmentProgress = findViewById(R.id.MinSegmentProgress);
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(() -> runThread());
            }

            public void runThread() {
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                secSegmentProgress.setValue((float) now.second);
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                minSegmentProgress.setValue((float) now.minute);
            }
        }, 0, 1000);
    }

    public void makeWatchMoving(int i) {
        if (i != 121) {
            switch (i) {
                case ClockNum.smart7 /* 126 */:
                    CustomAnalogClockView customAnalogClockView = findViewById(R.id.selectedClock);
                    customAnalogClockView.init(this, R.drawable.bg_smart_dial_7_2, R.drawable.needle_smart_hr_7, R.drawable.needle_smart_min_7, R.drawable.needle_smart_sec_7, 0, false, false);
                    customAnalogClockView.setAutoUpdate(true);
                    return;
                case ClockNum.smart8 /* 127 */:
                    CustomAnalogClockView customAnalogClockView2 = findViewById(R.id.selectedClock);
                    customAnalogClockView2.init(this, R.drawable.bg_smart_dial_8, R.drawable.needle_smart_hr_8, R.drawable.needle_smart_min_8, R.drawable.needle_smart_sec_8, 0, false, false);
                    customAnalogClockView2.setAutoUpdate(true);
                    return;
                case 128:
                    CustomAnalogClockView customAnalogClockView3 = findViewById(R.id.selectedClock);
                    customAnalogClockView3.init(this, R.drawable.bg_smart_dial_9_2, R.drawable.needle_smart_hr_9, R.drawable.needle_smart_min_9, R.drawable.needle_smart_sec_9, 0, false, false);
                    customAnalogClockView3.setAutoUpdate(true);
                    return;
                case ClockNum.smart10 /* 129 */:
                    CustomAnalogClockView customAnalogClockView4 = findViewById(R.id.selectedClock);
                    customAnalogClockView4.init(this, R.drawable.bg_smart_dial_10_2, R.drawable.needle_smart_hr_10, R.drawable.needle_smart_min_10, R.drawable.needle_smart_sec_10, 0, false, false);
                    customAnalogClockView4.setAutoUpdate(true);
                    return;
                default:
                    switch (i) {
                        case ClockNum.Anim1 /* 136 */:
                            CustomAnalogClockView customAnalogClockView5 = findViewById(R.id.clock_view);
                            customAnalogClockView5.init(this, R.drawable.bg_dial_animated1, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_yellow, 0, false, false);
                            customAnalogClockView5.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim1_1 /* 137 */:
                            CustomAnalogClockView customAnalogClockView6 = findViewById(R.id.clock_view);
                            customAnalogClockView6.init(this, R.drawable.bg_dial_animated0, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_yellow, 0, false, false);
                            customAnalogClockView6.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim1_2 /* 138 */:
                            CustomAnalogClockView customAnalogClockView7 = findViewById(R.id.clock_view);
                            customAnalogClockView7.init(this, R.drawable.bg_dial_animated3, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView7.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim1_3 /* 139 */:
                            CustomAnalogClockView customAnalogClockView8 = findViewById(R.id.clock_view);
                            customAnalogClockView8.init(this, R.drawable.bg_dial_animated2, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView8.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim2 /* 140 */:
                            CustomAnalogClockView customAnalogClockView9 = findViewById(R.id.clock_view);
                            customAnalogClockView9.init(this, R.drawable.bg_anim_dial_3, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView9.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim2_1 /* 141 */:
                            CustomAnalogClockView customAnalogClockView10 = findViewById(R.id.clock_view);
                            customAnalogClockView10.init(this, R.drawable.bg_anim_dial_4, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView10.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim2_2 /* 142 */:
                            CustomAnalogClockView customAnalogClockView11 = findViewById(R.id.clock_view);
                            customAnalogClockView11.init(this, R.drawable.bg_anim_dial_4, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView11.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim2_3 /* 143 */:
                            CustomAnalogClockView customAnalogClockView12 = findViewById(R.id.clock_view);
                            customAnalogClockView12.init(this, R.drawable.bg_anim_dial_6, R.drawable.needle_anim_h_6, R.drawable.needle_anim_m_6, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView12.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim3 /* 144 */:
                            CustomAnalogClockView customAnalogClockView13 = findViewById(R.id.clock_view);
                            customAnalogClockView13.init(this, R.drawable.layout_dddd, R.drawable.needle_hhhh, R.drawable.needle_m, R.drawable.needle_anim_s_11, 0, false, false);
                            customAnalogClockView13.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim4 /* 145 */:
                            CustomAnalogClockView customAnalogClockView14 = findViewById(R.id.clock_view);
                            customAnalogClockView14.init(this, R.drawable.bg_anim_dial_7, R.drawable.needle_anim_h_7, R.drawable.needle_anim_m_7, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView14.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim5 /* 146 */:
                            CustomAnalogClockView customAnalogClockView15 = findViewById(R.id.clock_view);
                            customAnalogClockView15.init(this, R.drawable.bg_anim_dial_12, R.drawable.needle_anim_h_10, R.drawable.needle_anim_m_10, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView15.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim6 /* 147 */:
                            CustomAnalogClockView customAnalogClockView16 = findViewById(R.id.clock_view);
                            customAnalogClockView16.init(this, R.drawable.bg_anim_dial_20, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView16.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim7 /* 148 */:
                            CustomAnalogClockView customAnalogClockView17 = findViewById(R.id.clock_view);
                            customAnalogClockView17.init(this, R.drawable.bg_anim_dial_19, R.drawable.needle_anim_h_19, R.drawable.needle_anim_m_19, R.drawable.needle_anim_s_19, 0, false, false);
                            customAnalogClockView17.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim8 /* 149 */:
                            CustomAnalogClockView customAnalogClockView18 = findViewById(R.id.clock_view);
                            customAnalogClockView18.init(this, R.drawable.bg_anim_dial_23, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView18.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim9 /* 150 */:
                            CustomAnalogClockView customAnalogClockView19 = findViewById(R.id.clock_view);
                            customAnalogClockView19.init(this, R.drawable.bg_anim_dial_25, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView19.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim9_1 /* 151 */:
                            CustomAnalogClockView customAnalogClockView20 = findViewById(R.id.clock_view);
                            customAnalogClockView20.init(this, R.drawable.bg_dial9_1, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView20.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim9_2 /* 152 */:
                            CustomAnalogClockView customAnalogClockView21 = findViewById(R.id.clock_view);
                            customAnalogClockView21.init(this, R.drawable.bg_dial9_2, R.drawable.needle_anim_h, R.drawable.needle_anim_m, R.drawable.needle_anim_s_red, 0, false, false);
                            customAnalogClockView21.setAutoUpdate(true);
                            return;
                        case ClockNum.Anim9_3 /* 153 */:
                            CustomAnalogClockView customAnalogClockView22 = findViewById(R.id.clock_view);
                            customAnalogClockView22.init(this, R.drawable.bg_dial9_3, R.drawable.needle_h9_3, R.drawable.needle_m9_3, R.drawable.needle_s9_3, 0, false, false);
                            customAnalogClockView22.setAutoUpdate(true);
                            return;
                        default:
                            return;
                    }
            }
        } else {
            CustomAnalogClockView customAnalogClockView23 = findViewById(R.id.selectedClock);
            customAnalogClockView23.init(this, R.drawable.smart_2_2, R.drawable.needle_smart_hr_2, R.drawable.needle_smart_min_2, R.drawable.needle_smart_sec_2, 0, false, false);
            customAnalogClockView23.setAutoUpdate(true);
        }
    }

    public void enableLEDWatch(int i) {
        switch (i) {
            case ClockNum.led1 /* 161 */:
                updateMinuteSecondProgress();
                return;
            case ClockNum.led2 /* 162 */:
            default:
                return;
            case ClockNum.led3 /* 163 */:
                updateSecondProgress();
                return;
            case ClockNum.led4 /* 164 */:
                updateMinuteSecondProgress();
                return;
            case ClockNum.led5 /* 165 */:
                updateSegmentMinuteSecondProgress();
                return;
            case ClockNum.led6 /* 166 */:
                updateSegmentSecondProgress();
                updateMinutesProgress();
                return;
            case ClockNum.led7 /* 167 */:
                updateSegmentSecondProgress();
                return;
            case ClockNum.led8 /* 168 */:
                updateSecondProgress();
                CustomAnalogClockView customAnalogClockView = findViewById(R.id.selectedClock);
                customAnalogClockView.init(this, R.drawable.bg_led8_dial, R.drawable.needle_led8_h, R.drawable.needle_led8_m, R.drawable.needle_smart_sec_7, 0, false, false);
                customAnalogClockView.setAutoUpdate(true);
                return;
            case ClockNum.led9 /* 169 */:
                updateSecondProgress();
                CustomAnalogClockView customAnalogClockView2 = findViewById(R.id.selectedClock);
                customAnalogClockView2.init(this, R.drawable.bg_led9_dial, R.drawable.needle_led8_h, R.drawable.needle_led8_m, R.drawable.needle_smart_sec_7, 0, false, false);
                customAnalogClockView2.setAutoUpdate(true);
                return;
            case ClockNum.led10 /* 170 */:
                updateMinuteSecondProgress();
                updateSegmentSecondProgress();
                return;
        }
    }

    public void updateMinuteSecondProgress() {
        this.secProgressBar = findViewById(R.id.seconds_progress_bar);
        this.minProgressBar = findViewById(R.id.minutes_progress_bar);
        new Timer().schedule(new TimerTask() {

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                runOnUiThread(() -> runThread());
            }

            public void runThread() {
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                secProgressBar.setProgress((float) now.second);
                now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                minProgressBar.setProgress((float) now.minute);
            }
        }, 0, 1000);
    }
}
