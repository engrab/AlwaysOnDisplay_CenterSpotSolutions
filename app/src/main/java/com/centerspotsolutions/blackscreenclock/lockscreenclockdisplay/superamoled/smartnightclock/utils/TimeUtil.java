package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils;

public class TimeUtil {

    public static String getTimeString(String str, String str2, String str3) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    c = 0;
                    break;
                }
                break;
            case 1567:
                if (str.equals("10")) {
                    c = 1;
                    break;
                }
                break;
            case 1568:
                if (str.equals("11")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                str = "12";
                break;
            case 1:
                str = "ten";
                break;
            case 2:
                str = "eleven";
                break;
        }
        if (str2.equals("0")) {
            return str + " o Clock";
        }
        return str + " " + str2 + " " + str3;
    }
}
