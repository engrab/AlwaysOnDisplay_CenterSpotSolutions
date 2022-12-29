package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen;

import android.content.DialogInterface;

public final class SettingsListener
        implements DialogInterface.OnClickListener {

    public static final SettingsListener INSTANCE
            = new SettingsListener();

    private SettingsListener() {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
