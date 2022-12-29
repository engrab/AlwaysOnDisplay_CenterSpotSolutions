package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.Calendar;

public class HandsDialOverlay implements DialOverlay {
    private Drawable mHour;
    private float mHourRotation;
    private float mMinRotation;
    private Drawable mMinute;
    private float mSecRot;
    private Drawable mSecond;
    private boolean mShowSeconds;
    private float scale;


    public HandsDialOverlay(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        this.mHour = drawable;
        this.mMinute = drawable2;
        this.mSecond = drawable3;
    }

    public HandsDialOverlay(Context context, int i, int i2) {
        context.getResources();

        Glide.with(context)
                .asDrawable()
                .load(Integer.valueOf(i2))
                .into((new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        HandsDialOverlay.this.mHour = resource;
                    }
                }));

//        Glide.with(context).asDrawable().load(Integer.valueOf(i2)).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() {
//            /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.clockworking.HandsOverlay.AnonymousClass1 */
//
//            @Override // com.bumptech.glide.request.target.Target
//            public /* bridge */ void onResourceReady(Object obj, Transition transition) {
//                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
//            }
//
//            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                HandsOverlay.this.mHour = drawable;
//            }
//        });

        Glide.with(context)
                .asDrawable()
                .load(Integer.valueOf(i2))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        HandsDialOverlay.this.mMinute = resource;
                    }
                });

//        Glide.with(context).asDrawable().load(Integer.valueOf(i2)).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() {
//            /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.clockworking.HandsOverlay.AnonymousClass2 */
//
//            @Override // com.bumptech.glide.request.target.Target
//            public /* bridge */ void onResourceReady(Object obj, Transition transition) {
//                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
//            }
//
//            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                HandsOverlay.this.mMinute = drawable;
//            }
//        });

        Glide.with(context)
                .asDrawable()
                .load(Integer.valueOf(i2))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        HandsDialOverlay.this.mSecond = resource;
                    }
                });

//        Glide.with(context).asDrawable().load(Integer.valueOf(i2)).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() {
//            /* class com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.clockworking.HandsOverlay.AnonymousClass3 */
//
//            @Override // com.bumptech.glide.request.target.Target
//            public /* bridge */ void onResourceReady(Object obj, Transition transition) {
//                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
//            }
//
//            public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
//                HandsOverlay.this.mSecond = drawable;
//            }
//        });
    }

    public HandsDialOverlay(Context context, boolean z) {
        context.getResources();
        this.mHour = null;
        this.mMinute = null;
        this.mSecond = null;
    }

    public static float getAnglesForHourNeedle(int i, int i2) {
        float f = (float) (i + 12);
        float f2 = AnalogClock.is24 ? 24.0f : 12.0f;
        return (((f / f2) * 360.0f) % 360.0f) + (((((float) i2) / 60.0f) * 360.0f) / f2);
    }

    private void drawSec(Canvas canvas, int i, int i2, int i3, int i4, Calendar calendar, boolean z) {
        canvas.rotate(this.mSecRot, (float) i, (float) i2);
        if (z) {
            int intrinsicWidth = ((int) (((float) this.mSecond.getIntrinsicWidth()) * this.scale)) / 2;
            int intrinsicHeight = ((int) (((float) this.mSecond.getIntrinsicHeight()) * this.scale)) / 2;
            this.mSecond.setBounds(i - intrinsicWidth, i2 - intrinsicHeight, i + intrinsicWidth, i2 + intrinsicHeight);
        }
        this.mSecond.draw(canvas);
    }

    private void drawHours(Canvas canvas, int i, int i2, int i3, int i4, Calendar calendar, boolean z) {
        canvas.rotate(this.mHourRotation, (float) i, (float) i2);
        if (z) {
            int intrinsicWidth = ((int) (((float) this.mHour.getIntrinsicWidth()) * this.scale)) / 2;
            int intrinsicHeight = ((int) (((float) this.mHour.getIntrinsicHeight()) * this.scale)) / 2;
            this.mHour.setBounds(i - intrinsicWidth, i2 - intrinsicHeight, i + intrinsicWidth, i2 + intrinsicHeight);
        }
        this.mHour.draw(canvas);
    }

    @Override
    // com.timeapp.smartwatch.nightwatch.clockstime.clockwidget.amoledclock.clockworking.DialOverlay
    public void onDraw(Canvas canvas, int i, int i2, int i3, int i4, Calendar calendar, boolean z) {
        updateNeedles(calendar);
        canvas.save();
        if (!AnalogClock.hourOnTop) {
            drawHours(canvas, i, i2, i3, i4, calendar, z);
        } else {
            drawminutes(canvas, i, i2, i3, i4, calendar, z);
        }
        canvas.restore();
        canvas.save();
        if (!AnalogClock.hourOnTop) {
            drawminutes(canvas, i, i2, i3, i4, calendar, z);
        } else {
            drawHours(canvas, i, i2, i3, i4, calendar, z);
        }
        canvas.restore();
        canvas.save();
        if (!AnalogClock.hourOnTop) {
            drawSec(canvas, i, i2, i3, i4, calendar, z);
        } else {
            drawSec(canvas, i, i2, i3, i4, calendar, z);
        }
        canvas.restore();
    }

    private void drawminutes(Canvas canvas, int i, int i2, int i3, int i4, Calendar calendar, boolean z) {
        canvas.rotate(this.mMinRotation, (float) i, (float) i2);
        if (z) {
            int intrinsicWidth = ((int) (((float) this.mMinute.getIntrinsicWidth()) * this.scale)) / 2;
            int intrinsicHeight = ((int) (((float) this.mMinute.getIntrinsicHeight()) * this.scale)) / 2;
            this.mMinute.setBounds(i - intrinsicWidth, i2 - intrinsicHeight, i + intrinsicWidth, i2 + intrinsicHeight);
        }
        this.mMinute.draw(canvas);
    }

    public void setshowseconds(boolean z) {
        this.mShowSeconds = z;
    }

    private void updateNeedles(Calendar calendar) {
        int i = calendar.get(11);
        int i2 = calendar.get(12);
        int i3 = calendar.get(13);
        this.mHourRotation = getAnglesForHourNeedle(i, i2);
        this.mMinRotation = ((((float) i2) / 60.0f) * 360.0f) + (this.mShowSeconds ? ((((float) i3) / 60.0f) * 360.0f) / 60.0f : 0.0f);
        this.mSecRot = ((float) i3) * 6.0f;
    }

    public HandsDialOverlay withScale(float f) {
        this.scale = f;
        return this;
    }

}
