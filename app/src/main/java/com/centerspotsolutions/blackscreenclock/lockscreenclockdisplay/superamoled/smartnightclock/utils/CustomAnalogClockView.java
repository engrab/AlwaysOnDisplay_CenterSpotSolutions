package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.R;
import com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.classes.HandsDialOverlay;
import com.tomerrosenfeld.customanalogclockview.DialOverlay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

public class CustomAnalogClockView extends View {
    static final boolean $assertionsDisabled = false;
    public static boolean hourOnTop;
    public static boolean is24;
    private final ArrayList<DialOverlay> mDialOverlay = new ArrayList<>();
    private boolean autoUpdate;
    private int mBottom;
    private Calendar mCalendar;
    private int mDialHeight;
    private int mDialWidth;
    private Drawable mFace;
    private HandsDialOverlay mHandsDialOverlay;
    private int mLeft;
    private int mRight;
    private boolean mSizeChanged;
    private int mTop;
    private int radius;
    private float sizeScale = 1.0f;

    public CustomAnalogClockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        handleAttrs(context, attributeSet);
    }

    public CustomAnalogClockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        handleAttrs(context, attributeSet);
    }

    public CustomAnalogClockView(Context context) {
        super(context);
        init(context);
    }

    public CustomAnalogClockView(Context context, boolean z) {
        super(context);
        if (z) {
            init(context);
        }
    }

    private void handleAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.CustomAnalogClock, 0, 0);
        if (!obtainStyledAttributes.hasValue(0) || obtainStyledAttributes.getBoolean(0, true)) {
            init(context);
            obtainStyledAttributes.recycle();
            return;
        }
        obtainStyledAttributes.recycle();
    }

    public void init(Context context) {
        init(context, R.drawable.bg_led9_dial, R.drawable.needle_smart_sec_10, R.drawable.needle_smart_min_10, R.drawable.needle_smart_hr_10, 0, false, false);
    }

    public void setScale(float f) {
        if (f > 0.0f) {
            this.sizeScale = f;
            this.mHandsDialOverlay.withScale(f);
            invalidate();
            return;
        }
        throw new IllegalArgumentException("Scale must be bigger than 0");
    }

    public void setFace(int i) {
        setFace(getResources().getDrawable(i));
    }

    public void init(Context context, int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        is24 = z;
        hourOnTop = z2;
        setFace(i);
        Drawable drawable = context.getResources().getDrawable(i2);
        if (i5 > 0) {
            drawable.setAlpha(i5);
        }
        Drawable drawable2 = context.getResources().getDrawable(i3);
        Drawable drawable3 = context.getResources().getDrawable(i4);
        this.mCalendar = Calendar.getInstance();
        HandsDialOverlay withScale = new HandsDialOverlay(drawable, drawable2, drawable3).withScale(this.sizeScale);
        this.mHandsDialOverlay = withScale;
        withScale.setshowseconds(true);
    }

    public void setFace(Drawable drawable) {
        this.mFace = drawable;
        this.mSizeChanged = true;
        this.mDialHeight = drawable.getIntrinsicHeight();
        int intrinsicWidth = this.mFace.getIntrinsicWidth();
        this.mDialWidth = intrinsicWidth;
        this.radius = Math.max(this.mDialHeight, intrinsicWidth);
        invalidate();
    }

    public void setTime(long j) {
        this.mCalendar.setTimeInMillis(j);
        invalidate();
    }

    public void setTime(Calendar calendar) {
        this.mCalendar = calendar;
        invalidate();
        if (this.autoUpdate) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setTime(Calendar.getInstance());
                }
            }, 1000);
        }
    }

    public void setAutoUpdate(boolean z) {
        this.autoUpdate = z;
        setTime(Calendar.getInstance());
    }

    public void setTimezone(TimeZone timeZone) {
        this.mCalendar = Calendar.getInstance(timeZone);
    }

    public void setHandsOverlay(HandsDialOverlay handsDialOverlay) {
        this.mHandsDialOverlay = handsDialOverlay;
    }

    @Override
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.mSizeChanged = true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        boolean z2 = this.mSizeChanged;
        this.mSizeChanged = false;
        int i = this.mRight - this.mLeft;
        int i2 = this.mBottom - this.mTop;
        int i3 = i / 2;
        int i4 = i2 / 2;
        float f = this.sizeScale;
        int i5 = (int) (((float) this.mDialWidth) * f);
        int i6 = (int) (((float) this.mDialHeight) * f);
        if (i < i5 || i2 < i6) {
            float min = Math.min(((float) i) / ((float) i5), ((float) i2) / ((float) i6));
            canvas.save();
            canvas.scale(min, min, (float) i3, (float) i4);
            z = true;
        } else {
            z = false;
        }
        if (z2) {
            int i7 = i5 / 2;
            int i8 = i6 / 2;
            this.mFace.setBounds(i3 - i7, i4 - i8, i7 + i3, i8 + i4);
        }
        this.mFace.draw(canvas);
        Iterator<DialOverlay> it = this.mDialOverlay.iterator();
        while (it.hasNext()) {
            it.next().onDraw(canvas, i3, i4, i5, i6, this.mCalendar, z2);
        }
        this.mHandsDialOverlay.onDraw(canvas, i3, i4, i5, i6, this.mCalendar, z2);
        if (z) {
            canvas.restore();
        }
    }

    @Override
    public void onMeasure(int i, int i2) {
        int i3 = (int) (((float) this.radius) * this.sizeScale);
        setMeasuredDimension(i3, i3);
    }

    @Override
    public int getSuggestedMinimumHeight() {
        return (int) (((float) this.mDialHeight) * this.sizeScale);
    }

    @Override
    public int getSuggestedMinimumWidth() {
        return (int) (((float) this.mDialWidth) * this.sizeScale);
    }

    @Override
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mRight = i3;
        this.mLeft = i;
        this.mTop = i2;
        this.mBottom = i4;
    }

    public void addDialOverlay(DialOverlay dialOverlay) {
        this.mDialOverlay.add(dialOverlay);
    }

    public void removeDialOverlay(DialOverlay dialOverlay) {
        this.mDialOverlay.remove(dialOverlay);
    }

    public void clearDialOverlays() {
        this.mDialOverlay.clear();
    }
}
