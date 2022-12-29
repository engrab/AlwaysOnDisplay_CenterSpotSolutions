package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen;

import android.content.DialogInterface;

public final class OverAppsListener implements
        DialogInterface.OnClickListener {

    public static final OverAppsListener INSTANCE
            = new OverAppsListener();

    private OverAppsListener() {
    }

    @Override
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
