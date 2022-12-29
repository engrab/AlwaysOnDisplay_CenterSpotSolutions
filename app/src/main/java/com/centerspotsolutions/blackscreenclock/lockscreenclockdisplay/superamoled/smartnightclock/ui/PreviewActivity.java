package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.telephony.TelephonyManager;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.GestureDetectorCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ClockNum;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ConfigActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.SharedPref;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.TimeUtil;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.Variables;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.skydoves.medal.MedalAnimation;
import com.skydoves.medal.MedalDirection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class PreviewActivity extends BaseActivity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        TextToSpeech.OnInitListener {

    private static final String TAG = "AmoledScreen";
    public SharedPref mSharedPref;
    View view;
    Handler handler = new Handler();
    int hourHand;
    ImageView image1;
    ImageView image2;
    TextView tvBattery;
    private final BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.AmoledScreen.AnonymousClass1 */

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String string;
            Log.i("onReceive", "onReceive: " + intent.getAction());
            try {
                if (intent.getAction() == null || intent.getAction().equals("android.intent.action.BATTERY_CHANGED") ||
                        (string = intent.getExtras().getString("state")) == null ||
                        string.isEmpty() || !string.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    try {
                        int intExtra = intent.getIntExtra("level", 0);
                        int intExtra2 = intent.getIntExtra("plugged", -1);
                        if (intExtra2 == 1 || intExtra2 == 2 || intExtra2 == 4) {
                            TextView textView = PreviewActivity.this.tvBattery;
                            textView.setText("Charging " + intExtra + " %");
                            return;
                        }
                        TextView textView2 = PreviewActivity.this.tvBattery;
                        textView2.setText(intExtra + " %");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    PreviewActivity.this.finish();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    };
    ImageView image4;
    int minuteHand;
    ArrayList<String> packageNames = new ArrayList<>();
    Animation mAnimation;
    int clockNumber;
    ImageView image3;
    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.AmoledScreen.AnonymousClass2 */

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("message");
            Log.i("receiver", "Got message: " + stringExtra);
            if (stringExtra != null && !stringExtra.isEmpty()) {
                try {
                    if (!PreviewActivity.this.packageNames.contains(stringExtra)) {
                        PreviewActivity.this.packageNames.add(stringExtra);
                        final Drawable applicationIcon = PreviewActivity.this.getPackageManager().getApplicationIcon(stringExtra);
                        if (applicationIcon != null) {
                            PreviewActivity.this.runOnUiThread(new Runnable() {
                                /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.AmoledScreen.AnonymousClass2.AnonymousClass1 */

                                @Override // java.lang.Runnable
                                public void run() {
                                    PreviewActivity.this.setNotificationIcon(applicationIcon);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    int secondHand;
    SharedPreferences sharedPreferences;
    int watchFace;
    Animation rotate;
    private GestureDetectorCompat mDetector;
    private int currentApiVersion;
    private TextToSpeech textToSpeech;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        this.mSharedPref = new SharedPref(getApplicationContext());
        getWindow().addFlags(6816896);
        this.sharedPreferences = getSharedPreferences("Settings", 0);
        this.clockNumber = 1;
        if (getIntent() == null || getIntent().getExtras() == null) {
            this.clockNumber = this.sharedPreferences.getInt(Variables.CLOCK_NUMBER, 1);
            this.watchFace = this.sharedPreferences.getInt(Variables.WATCH_FACE, 1);
            this.hourHand = this.sharedPreferences.getInt(Variables.HOUR_NEEDLE, 1);
            this.minuteHand = this.sharedPreferences.getInt(Variables.MINUTE_NEEDLE, 1);
            this.secondHand = this.sharedPreferences.getInt(Variables.SECOND_NEEDLE, 1);
        } else {
            this.clockNumber = getIntent().getIntExtra("clockNum", 1);
            this.watchFace = SecondAnalogActivity.selectedWatchFace;
            this.hourHand = SecondAnalogActivity.selectedHourNeedle;
            this.minuteHand = SecondAnalogActivity.selectedMinuteNeedle;
            this.secondHand = SecondAnalogActivity.selectedSecondNeedle;
        }
        int i = this.clockNumber;

        if (i > 70 && i < 100) {
            setContentView(R.layout.calendar_display_layout);
            showCalendar();
        } else if (i >= 101 && i <= 116) {
            switch (i) {
                case 101:
                    setContentView(R.layout.layout_digital1);
                    break;
                case 102:
                    setContentView(R.layout.layout_digital2);
                    break;
                case 103:
                    setContentView(R.layout.layout_digital3);
                    break;
                case 104:
                    setContentView(R.layout.layout_digital4);
                    break;
                case 105:
                    setContentView(R.layout.layout_digital5);
                    break;
                case 106:
                    setContentView(R.layout.layout_digital6);
                    break;
                case 107:
                    setContentView(R.layout.layout_digital7);
                    break;
                case 108:
                    setContentView(R.layout.layout_digital8);
                    break;
                case 109:
                    setContentView(R.layout.layout_digital9);
                    break;
                case 110:
                    setContentView(R.layout.layout_digital10);
                    break;
                case 111:
                    setContentView(R.layout.layout_digital11);
                    break;
                case 112:
                    setContentView(R.layout.layout_digital12);
                    break;
                case 113:
                    setContentView(R.layout.layout_digital13);
                    break;
                case 114:
                    setContentView(R.layout.layout_digital14);
                    break;
                case 115:
                    setContentView(R.layout.layout_digital15);
                    break;
                case 116:
                    setContentView(R.layout.layout_digital16);
                    break;
            }
            this.view = findViewById(R.id.relative_Layout_wid2layout);
            setAnimation();
        } else if (i >= 120 && i <= 131) {
            switch (i) {
                case 120:
                    setContentView(R.layout.layout_smart1);
                    break;
                case 121:
                    setContentView(R.layout.layout_smart2);
                    break;
                case 122:
                    setContentView(R.layout.layout_smart3);
                    break;
                case 123:
                    setContentView(R.layout.layout_smart4);
                    break;
                case 124:
                    setContentView(R.layout.layout_smart5);
                    break;
                case ClockNum.smart6 /* 125 */:
                    setContentView(R.layout.layout_smart6);
                    break;
                case ClockNum.smart7 /* 126 */:
                    setContentView(R.layout.layout_smart7);
                    break;
                case ClockNum.smart8 /* 127 */:
                    setContentView(R.layout.layout_smart8);
                    break;
                case 128:
                    setContentView(R.layout.layout_smart9);
                    break;
                case ClockNum.smart10 /* 129 */:
                    setContentView(R.layout.layout_smart10);
                    break;
                case ClockNum.smart11 /* 130 */:
                    setContentView(R.layout.layout_smart11);
                    break;
                case ClockNum.smart12 /* 131 */:
                    setContentView(R.layout.layout_smart12);
                    break;
            }
            makeWatchMoving(this.clockNumber);
        } else if (i < 136 || i > 153) {
            if (i < 161 || i > 173) {
                if (i < 31 || i > 42) {
                    setContentView(R.layout.activity_display_layout);
                    this.view = findViewById(R.id.linear_layout_clocks_layout);
                    View findViewById = findViewById(R.id.linear_layout_analog_digital_time);
                    int i2 = this.clockNumber;
                    if (i2 <= 30 || i2 >= 43) {
                        findViewById.setVisibility(View.VISIBLE);
                    } else {
                        findViewById.setVisibility(View.GONE);
                    }
                    new ConfigActivity().configureClock(findViewById(R.id.clock_view), this, this.clockNumber);
                    /** commenting out setAnimation() as AnalogClocks won't have "Animations'feature" **/
//                    SetAnimation();
                } else {
                    switch (i) {
                        case 31:
                            setContentView(R.layout.layout_emoji_clock1);
                            break;
                        case 32:
                            setContentView(R.layout.layout_emoji_clock2);
                            break;
                        case 33:
                            setContentView(R.layout.layout_emoji_clock3);
                            break;
                        case 34:
                            setContentView(R.layout.layout_emoji_clock4);
                            break;
                        case 35:
                            setContentView(R.layout.layout_emoji_clock5);
                            break;
                        case 36:
                            setContentView(R.layout.layout_emoji_clock6);
                            break;
                        case 37:
                            setContentView(R.layout.layout_emoji_clock7);
                            break;
                        case 38:
                            setContentView(R.layout.layout_emoji_clock8);
                            break;
                        case 39:
                            setContentView(R.layout.layout_emoji_clock9);
                            break;
                        case 40:
                            setContentView(R.layout.layout_emoji_clock10);
                            break;
                        case 41:
                            setContentView(R.layout.layout_emoji_clock11);
                            break;
                        case 42:
                            setContentView(R.layout.layout_emoji_clock12);
                            break;
                    }
                    changeBackgroundColor();
                }
            } else if (i > 163) {
                switch (i) {
                    case ClockNum.led4 /* 164 */:
                        setContentView(R.layout.layout_led_clock_4);
                        break;
                    case ClockNum.led5 /* 165 */:
                        setContentView(R.layout.layout_led_clock_5);
                        break;
                    case ClockNum.led6 /* 166 */:
                        setContentView(R.layout.layout_led_clock_6);
                        break;
                    case ClockNum.led7 /* 167 */:
                        setContentView(R.layout.layout_led_clock_7);
                        break;
                    case ClockNum.led8 /* 168 */:
                        setContentView(R.layout.layout_led_clock_8);
                        break;
                    case ClockNum.led9 /* 169 */:
                        setContentView(R.layout.layout_led_clock_9);
                        break;
                    case ClockNum.led10 /* 170 */:
                        setContentView(R.layout.layout_led_clock_10);
                        break;
                    case ClockNum.led11 /* 171 */:
                        setContentView(R.layout.layout_led_clock_11);
                        break;
                    case ClockNum.led12 /* 172 */:
                        setContentView(R.layout.layout_led_clock_12);
                        break;
                    case ClockNum.led13 /* 173 */:
                        setContentView(R.layout.layout_led_clock_2);
                        break;
                }
                enableLEDWatch(this.clockNumber);
            } else if (i >= 161 && i <= 163) {
                switch (this.clockNumber) {
                    case ClockNum.led1 /* 161 */:
                        setContentView(R.layout.layout_led_clock_1);
                        break;
                    case ClockNum.led2 /* 162 */:
                        setContentView(R.layout.layout_led_clock_2);
                        break;
                    case ClockNum.led3 /* 163 */:
                        setContentView(R.layout.layout_led_clock_3);
                        break;
                }
                enableLEDWatch(this.clockNumber);
            } else {
                enableLEDWatch(this.clockNumber);
            }
        } else if (i < 136 || i > 139) {
            if (i < 140 || i > 143) {
                if (i < 150 || i > 153) {
                    switch (i) {
                        case ClockNum.Anim3 /* 144 */:
                            setContentView(R.layout.animated_clock_3);
                            break;
                        case ClockNum.Anim4 /* 145 */:
                            setContentView(R.layout.animated_clock_4);
                            break;
                        case ClockNum.Anim5 /* 146 */:
                            setContentView(R.layout.animated_clock_5);
                            break;
                        case ClockNum.Anim6 /* 147 */:
                            setContentView(R.layout.animated_clock_6);
                            break;
                        case ClockNum.Anim7 /* 148 */:
                            setContentView(R.layout.animated_clock_7);
                            break;
                        case ClockNum.Anim8 /* 149 */:
                            setContentView(R.layout.animated_clock_8);
                            break;
                    }
                    makeWatchMoving(this.clockNumber);
                } else if (i >= 150 && i <= 153) {
                    switch (this.clockNumber) {
                        case ClockNum.Anim9 /* 150 */:
                            setContentView(R.layout.animated_clock_9);
                            break;
                        case ClockNum.Anim9_1 /* 151 */:
                            setContentView(R.layout.animated_clock_9_1);
                            break;
                        case ClockNum.Anim9_2 /* 152 */:
                            setContentView(R.layout.animated_clock_9_2);
                            break;
                        case ClockNum.Anim9_3 /* 153 */:
                            setContentView(R.layout.animated_clock_9_3);
                            break;
                    }
                    makeWatchMoving(this.clockNumber);
                    Log.d(TAG, "onCreate: called");
                } else {
                    Log.d(TAG, "onCreate: called");
//                    setContentView(R.layout.expirelayout);
                    makeWatchMoving(this.clockNumber);
                }
            } else if (i >= 140 || i <= 143) {
                switch (this.clockNumber) {
                    case ClockNum.Anim2 /* 140 */:
                        setContentView(R.layout.animated_clock_2);
                        break;
                    case ClockNum.Anim2_1 /* 141 */:
                        setContentView(R.layout.animated_clock_2_1);
                        break;
                    case ClockNum.Anim2_2 /* 142 */:
                        setContentView(R.layout.animated_clock_2_2);
                        break;
                    case ClockNum.Anim2_3 /* 143 */:
                        setContentView(R.layout.animated_clock_2_3);
                        break;
                }
                makeWatchMoving(this.clockNumber);
            } else {
//                setContentView(R.layout.expirelayout);
                makeWatchMoving(this.clockNumber);
            }
        } else if (i >= 136 || i <= 139) {
            switch (this.clockNumber) {
                case ClockNum.Anim1 /* 136 */:
                    setContentView(R.layout.animated_clock_1);
                    break;
                case ClockNum.Anim1_1 /* 137 */:
                    setContentView(R.layout.animated_clock_1_1);
                    break;
                case ClockNum.Anim1_2 /* 138 */:
                    setContentView(R.layout.animated_clock_1_2);
                    break;
                case ClockNum.Anim1_3 /* 139 */:
                    setContentView(R.layout.animated_clock_1_3);
                    break;
            }
            makeWatchMoving(this.clockNumber);
        } else {
//            setContentView(R.layout.expirelayout);
            makeWatchMoving(this.clockNumber);
        }


        this.tvBattery = findViewById(R.id.text_view_battery);
        this.image1 = findViewById(R.id.image_VIEW_1);
        this.image2 = findViewById(R.id.image_VIEW2);
        this.image3 = findViewById(R.id.imageview3);
        this.image4 = findViewById(R.id.imageview4);
        int i3 = Build.VERSION.SDK_INT;
        this.currentApiVersion = i3;
        if (i3 >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
            final View decorView = getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {


                @Override // android.view.View.OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int i) {
                    if ((i & 4) == 0) {
                        decorView.setSystemUiVisibility(5894);
                    }
                }
            });
        }
        screenBrightness(this.sharedPreferences.getInt("brightness", 40));
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(this, this);
        this.mDetector = gestureDetectorCompat;
        gestureDetectorCompat.setOnDoubleTapListener(this);
    }

    @Override

    public void onDestroy() {
        try {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages(null);
            }
            if (this.mDetector != null) {
                this.mDetector = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.packageNames.clear();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onStop() {
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mMessageReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public void onInit(int i) {
        if (i == 0) {
            int language = this.textToSpeech.setLanguage(Locale.ENGLISH);
            if (language == -1 || language == -2) {
                Log.e("error", "This Language is not supported");
            } else {
                ConvertTextToSpeech();
            }
            this.textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.AmoledScreen.AnonymousClass6 */

                @Override // android.speech.tts.UtteranceProgressListener
                public void onDone(String str) {
                }

                @Override // android.speech.tts.UtteranceProgressListener
                public void onError(String str) {
                }

                @Override // android.speech.tts.UtteranceProgressListener
                public void onStart(String str) {
                }
            });
            return;
        }
        Log.e("error", "Initilization Failed!");
    }

    private void ConvertTextToSpeech() {
        Calendar instance = Calendar.getInstance();
        String valueOf = String.valueOf(instance.get(10));
        String valueOf2 = String.valueOf(instance.get(12));
        int i = instance.get(9);
        String GetTimeString = TimeUtil.getTimeString(valueOf, valueOf2, i == 0 ? "AM" : i == 1 ? "PM" : "0");
        if (Build.VERSION.SDK_INT >= 21) {
            this.textToSpeech.speak(GetTimeString, 0, null, "android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED");
        } else {
            this.textToSpeech.speak(GetTimeString, 0, null);
        }
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        this.textToSpeech = new TextToSpeech(this, this);
        Log.d("asfsgfsg", "onLongPress: ");
    }

    private void screenBrightness(double d) {

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = ((float) d) / 255.0f;
        getWindow().setAttributes(attributes);
    }

    @Override
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.currentApiVersion >= 19 && z) {
            getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mDetector.onTouchEvent(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d("asfsgfsg", "onSingleTapUp: ");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        finish();
        Log.d("asfsgfsg", "onDoubleTap: ");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        finish();
        return true;
    }

    @Override
    public void onPause() {
        try {
            unregisterReceiver(this.mBatInfoReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mMessageReceiver);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onPause();
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        Log.d("asfsgfsg", "onSingleTapConfirmed: ");
        return false;
    }

    private void setNotificationIcon(Drawable drawable) {
        try {
            if (!this.image1.isShown()) {
                this.image1.setVisibility(View.VISIBLE);
                this.image1.setImageDrawable(drawable);
            } else if (!this.image2.isShown()) {
                this.image2.setVisibility(View.VISIBLE);
                this.image2.setImageDrawable(drawable);
            } else if (!this.image3.isShown()) {
                this.image3.setVisibility(View.VISIBLE);
                this.image3.setImageDrawable(drawable);
            } else if (!this.image4.isShown()) {
                this.image4.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAnimation() {
        if (this.mSharedPref.getBoolean(ClockNum.IS_ANIM)) {
            int i = this.mSharedPref.getInt(ClockNum.ANIM_SPEED);
            switch (this.mSharedPref.getInt(ClockNum.ANIM_CASE)) {
                case 0:
                    this.view.clearAnimation();
                    return;
                case 1:
                    this.view.clearAnimation();
                    Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    this.mAnimation = loadAnimation;
                    loadAnimation.setDuration(i);
                    this.view.startAnimation(this.mAnimation);
                    return;
                case 2:
                    this.view.clearAnimation();
                    Animation loadAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                    this.rotate = loadAnimation2;
                    loadAnimation2.setDuration(i);
                    this.view.startAnimation(this.rotate);
                    return;
                case 3:
                    this.view.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setSpeed(i).build());
                    return;
                case 4:
                    this.view.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.LEFT).setSpeed(i).build());
                    return;
                case 5:
                    this.view.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setDegreeX(180).setSpeed(i).build());
                    return;
                case 6:
                    this.view.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setDegreeX(360).setSpeed(i).build());
                    return;
                default:
                    return;
            }
        }
    }

    private void showCalendar() {
        MaterialCalendarView materialCalendarView = findViewById(R.id.materialCalendarView);
        materialCalendarView.setPagingEnabled(false);
        materialCalendarView.setTopbarVisible(false);
        materialCalendarView.setSelectionColor(-1);
        materialCalendarView.setWeekDayTextAppearance(R.style.CustomTextAppearanceCal);
        materialCalendarView.setDateTextAppearance(R.style.CustomTextAppearanceCalDay);
        materialCalendarView.addDecorator(new DayViewDecorator() {

            @Override // com.prolificinteractive.materialcalendarview.DayViewDecorator
            public boolean shouldDecorate(CalendarDay calendarDay) {
//                return calendarDay.getCalendar().get(7) == 1;
                // TODO: check it again
                return CalendarDay.today().getDay() == 1;
            }

            @Override // com.prolificinteractive.materialcalendarview.DayViewDecorator
            public void decorate(DayViewFacade dayViewFacade) {
                dayViewFacade.addSpan(new ForegroundColorSpan(SupportMenu.CATEGORY_MASK));
            }
        });
        materialCalendarView.addDecorator(new DayViewDecorator() {
            /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.AmoledScreen.AnonymousClass5 */

            @Override // com.prolificinteractive.materialcalendarview.DayViewDecorator
            public boolean shouldDecorate(CalendarDay calendarDay) {
                CalendarDay calendarDay2 = CalendarDay.today();
                return calendarDay2 != null && calendarDay2.equals(calendarDay);
            }

            @Override // com.prolificinteractive.materialcalendarview.DayViewDecorator
            public void decorate(DayViewFacade dayViewFacade) {
                dayViewFacade.addSpan(new ForegroundColorSpan(-16711936));
            }
        });
    }

    @Override
    public void onResume() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            intentFilter.addAction("android.intent.action.PHONE_STATE");
            registerReceiver(this.mBatInfoReceiver, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            LocalBroadcastManager.getInstance(this).registerReceiver(this.mMessageReceiver, new IntentFilter("notification_added"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.onResume();
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        try {
            if (Math.abs(motionEvent.getY() - motionEvent2.getY()) > 250.0f) {
                return false;
            }
            if (motionEvent.getX() - motionEvent2.getX() > 120.0f && Math.abs(f) > 200.0f) {
                Log.v("motionEvent1", "Right to Left");
                finish();
                return true;
            } else if (motionEvent2.getX() - motionEvent.getX() <= 120.0f || Math.abs(f) <= 200.0f) {
                return true;
            } else {
                Log.v("motionEvent1", "Left to Right");
                finish();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}