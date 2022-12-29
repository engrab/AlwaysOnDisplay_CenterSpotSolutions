package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen;

import android.content.DialogInterface;

public final class AnalogListener
        implements DialogInterface.OnClickListener {
    public static final AnalogListener
            INSTANCE = new AnalogListener();

    private AnalogListener() {
    }

    @Override
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
