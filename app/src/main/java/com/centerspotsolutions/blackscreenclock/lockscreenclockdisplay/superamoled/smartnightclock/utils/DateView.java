package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils;

import android.content.Context;
import android.util.AttributeSet;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateView extends androidx.appcompat.widget.AppCompatTextView {

    public DateView(Context context) {
        super(context);
        setDate();
    }

    public DateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDate();
    }

    public DateView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setDate();
    }

    private void setDate() {
        setText(new SimpleDateFormat("EEE, d MMMM ").format(Calendar.getInstance().getTime()));
    }
}
