package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.AnimationAdapter;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ConfigActivity;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.ClockNum;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils.SharedPref;
import com.skydoves.medal.MedalAnimation;
import com.skydoves.medal.MedalDirection;

public class AnimatedActivity extends BaseActivity implements AnimationAdapter.OnAnimationClickListener {

    public static FrameLayout mFrameLayout;
    private final int[] IconsList = {R.drawable.ic_none, R.drawable.icon_blink, R.drawable.icon_rotate, R.drawable.icon_right, R.drawable.icon_left, R.drawable.icon_180, R.drawable.icon_360};
    private LayoutInflater mInflater;
    private AnimationAdapter mAnimAdapter;
    private RecyclerView mRecyclerView;
    private Animation mAnimation;
    private Animation mRotate;
    private boolean dbIsAnim;
    private SeekBar mSeekBar;
    private View mView;
    private int mClockNumber;
    private Button btnDone;
    private SharedPref mSharedPref;
    private int mSpeed;
    private int cassse;
    private int max;
    int mAnimSpeed;




    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_animated);
        init();
        createListeners();
        setClockAnimation();
    }

    @Override
    public void OnClickListener(int i) {
        Log.d("khfgsj", "OnClickListener: " + i);
        AnimationAdapter.rowIndex = i;
        this.mAnimAdapter.notifyDataSetChanged();
        switch (i) {
            case 0:
                mFrameLayout.clearAnimation();
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, false);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 0);
                this.mSharedPref.putInt(ClockNum.ANIM_SPEED, 10000);
                return;
            case 1:
                mFrameLayout.clearAnimation();
                Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                this.mAnimation = loadAnimation;
                loadAnimation.setDuration(this.mAnimSpeed);
                mFrameLayout.startAnimation(this.mAnimation);
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, true);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 1);
                return;
            case 2:
                mFrameLayout.clearAnimation();
                Animation loadAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                this.mRotate = loadAnimation2;
                loadAnimation2.setDuration(this.mAnimSpeed);
                mFrameLayout.startAnimation(this.mRotate);
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, true);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 2);
                return;
            case 3:
                mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setSpeed(this.mAnimSpeed).build());
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, true);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 3);
                return;
            case 4:
                mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.LEFT).setSpeed(this.mAnimSpeed).build());
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, true);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 4);
                return;
            case 5:
                mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setDegreeX(180).setSpeed(this.mAnimSpeed).build());
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, true);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 5);
                return;
            case 6:
                mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setDegreeX(360).setSpeed(this.mAnimSpeed).build());
                this.mSharedPref.putBoolean(ClockNum.IS_ANIM, true);
                this.mSharedPref.putInt(ClockNum.ANIM_CASE, 6);
                return;
            default:
                return;
        }
    }

    private void setClockAnimation() {
        int i = this.mClockNumber;
        if (i >= 101 && i <= 116) {
            switch (i) {
                case 101:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital1, null);
                    break;
                case 102:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital2, null);
                    break;
                case 103:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital3, null);
                    break;
                case 104:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital4, null);
                    break;
                case 105:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital5, null);
                    break;
                case 106:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital6, null);
                    break;
                case 107:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital7, null);
                    break;
                case 108:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital8, null);
                    break;
                case 109:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital9, null);
                    break;
                case 110:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital10, null);
                    break;
                case 111:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital11, null);
                    break;
                case 112:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital12, null);
                    break;
                case 113:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital13, null);
                    break;
                case 114:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital14, null);
                    break;
                case 115:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital15, null);
                    break;
                case 116:
                    this.mView = this.mInflater.inflate(R.layout.layout_digital16, null);
                    break;
            }
            View view = this.mView;
            if (view != null) {
                mFrameLayout.addView(view);
            }
        } else if (i >= 120 && i <= 131) {
            switch (i) {
                case 120:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart1, null);
                    break;
                case 121:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart2, null);
                    break;
                case 122:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart3, null);
                    break;
                case 123:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart4, null);
                    break;
                case 124:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart5, null);
                    break;
                case ClockNum.smart6 /* 125 */:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart6, null);
                    break;
                case ClockNum.smart7 /* 126 */:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart7, null);
                    break;
                case ClockNum.smart8 /* 127 */:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart8, null);
                    break;
                case 128:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart9, null);
                    break;
                case ClockNum.smart10 /* 129 */:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart10, null);
                    break;
                case ClockNum.smart11 /* 130 */:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart11, null);
                    break;
                case ClockNum.smart12 /* 131 */:
                    this.mView = this.mInflater.inflate(R.layout.layout_smart12, null);
                    break;
            }
            View view2 = this.mView;
            if (view2 != null) {
                mFrameLayout.addView(view2);
            }
            makeWatchMoving(this.mClockNumber);
        } else if (i < 136 || i > 153) {
            View inflate = this.mInflater.inflate(R.layout.analog_anim_layout, null);
            this.mView = inflate;
            mFrameLayout.addView(inflate);
            View findViewById = findViewById(R.id.linear_layout_analog_digital_time);
            int i2 = this.mClockNumber;
            if (i2 <= 30 || i2 >= 43) {
                findViewById.setVisibility(View.VISIBLE);
            } else {
                findViewById.setVisibility(View.GONE);
            }
            new ConfigActivity().configureClock(findViewById(R.id.clock_view), this, this.mClockNumber);
        } else {
            switch (i) {
                case ClockNum.Anim1 /* 136 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_1, null);
                    break;
                case ClockNum.Anim1_1 /* 137 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_1_1, null);
                    break;
                case ClockNum.Anim1_2 /* 138 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_1_2, null);
                    break;
                case ClockNum.Anim1_3 /* 139 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_1_3, null);
                    break;
                case ClockNum.Anim2 /* 140 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_2, null);
                    break;
                case ClockNum.Anim2_1 /* 141 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_2_1, null);
                    break;
                case ClockNum.Anim2_2 /* 142 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_2_2, null);
                    break;
                case ClockNum.Anim2_3 /* 143 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_2_3, null);
                    break;
                case ClockNum.Anim3 /* 144 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_3, null);
                    break;
                case ClockNum.Anim4 /* 145 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_4, null);
                    break;
                case ClockNum.Anim5 /* 146 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_5, null);
                    break;
                case ClockNum.Anim6 /* 147 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_6, null);
                    break;
                case ClockNum.Anim8 /* 149 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_8, null);
                    break;
                case ClockNum.Anim9 /* 150 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_9, null);
                    break;
                case ClockNum.Anim9_1 /* 151 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_9_1, null);
                    break;
                case ClockNum.Anim9_2 /* 152 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_9_2, null);
                    break;
                case ClockNum.Anim9_3 /* 153 */:
                    this.mView = this.mInflater.inflate(R.layout.animated_clock_9_3, null);
                    break;
            }
            View view3 = this.mView;
            if (view3 != null) {
                mFrameLayout.addView(view3);
            }
            makeWatchMoving(this.mClockNumber);
        }
    }

    private void init() {
        this.btnDone = findViewById(R.id.button_done);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_Anim);
        this.mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        AnimationAdapter animAdapter = new AnimationAdapter(this, this.IconsList, this);
        this.mAnimAdapter = animAdapter;
        this.mRecyclerView.setAdapter(animAdapter);
        this.mSharedPref = new SharedPref(getApplicationContext());
        mFrameLayout = findViewById(R.id.animation_preview_framelayout);
        this.mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        SeekBar seekBar = findViewById(R.id.seekBarSpeed);
        this.mSeekBar = seekBar;
        this.max = 10000;
        this.mAnimSpeed = 10000;
        seekBar.setMax(10000);
        this.mClockNumber = 1;
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            this.mClockNumber = getIntent().getIntExtra("clockNum", 1);
        }
        boolean z = this.mSharedPref.getBoolean(ClockNum.IS_ANIM);
        this.dbIsAnim = z;
        if (z) {
            this.mSpeed = this.mSharedPref.getInt(ClockNum.ANIM_SPEED);
            this.cassse = this.mSharedPref.getInt(ClockNum.ANIM_CASE);
            int i = this.mSpeed;
            if (i <= 1000) {
                this.mSeekBar.setProgress(this.max);
            } else if (i >= 9000) {
                this.mSeekBar.setProgress(1000);
            } else if (i > 1000 && i < 9000) {
                this.mSeekBar.setProgress(this.max - i);
            }
            this.mAnimSpeed = this.mSpeed;
            AnimationAdapter.rowIndex = this.cassse;
            setAnimationPreviewWithSpeed();
        }
    }

    private void createListeners() {
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Log.d("sgdssfdh", "onProgressChanged: " + i + "  " + AnimatedActivity.this.mAnimSpeed);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("sgdssfdh", "onStartTrackingTouch: ");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                if (progress <= 1000) {
                    AnimatedActivity.this.mAnimSpeed = 10000;
                } else if (progress > 1000 && progress <= 9000) {
                    AnimatedActivity animationPreview = AnimatedActivity.this;
                    animationPreview.mAnimSpeed = animationPreview.max - progress;
                } else if (progress > 9000) {
                    AnimatedActivity.this.mAnimSpeed = 1000;
                }
                AnimatedActivity.this.setAnimationPreviewWithSpeed();
                Log.d("sgdssfdh", "onStopTrackingTouch: ");
            }
        });
        this.btnDone.setOnClickListener(view -> AnimatedActivity.this.finishPreview(view));
    }

    private void setAnimationPreviewWithSpeed() {
        if (this.mSharedPref.getBoolean(ClockNum.IS_ANIM)) {
            this.mSharedPref.putInt(ClockNum.ANIM_SPEED, this.mAnimSpeed);
            switch (this.mSharedPref.getInt(ClockNum.ANIM_CASE)) {
                case 0:
                    mFrameLayout.clearAnimation();
                    return;
                case 1:
                    mFrameLayout.clearAnimation();
                    Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    this.mAnimation = loadAnimation;
                    loadAnimation.setDuration(this.mAnimSpeed);
                    mFrameLayout.startAnimation(this.mAnimation);
                    return;
                case 2:
                    mFrameLayout.clearAnimation();
                    Animation loadAnimation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                    this.mRotate = loadAnimation2;
                    loadAnimation2.setDuration(this.mAnimSpeed);
                    mFrameLayout.startAnimation(this.mRotate);
                    return;
                case 3:
                    mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setSpeed(this.mAnimSpeed).build());
                    return;
                case 4:
                    mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.LEFT).setSpeed(this.mAnimSpeed).build());
                    return;
                case 5:
                    mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setDegreeX(180).setSpeed(this.mAnimSpeed).build());
                    return;
                case 6:
                    mFrameLayout.startAnimation(new MedalAnimation.Builder().setDirection(MedalDirection.RIGHT).setDegreeX(360).setSpeed(this.mAnimSpeed).build());
                    return;
                default:
                    return;
            }
        }
    }

    public void finishPreview(View view) {
        finish();
    }


}
