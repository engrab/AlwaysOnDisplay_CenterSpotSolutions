package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.listen;

import android.content.DialogInterface;

public final class PermissionListener implements DialogInterface.OnClickListener {
    public static final PermissionListener INSTANCE =
            new PermissionListener();

    private PermissionListener() {
    }

    @Override
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
    }
}
