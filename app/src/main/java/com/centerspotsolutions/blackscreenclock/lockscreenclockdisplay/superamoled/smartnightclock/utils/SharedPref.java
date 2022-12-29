package com.centerspotsolutions.blackscreenclock.lockscreenclockdisplay.superamoled.smartnightclock.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

public class SharedPref {

    private final SharedPreferences sharedPreferences;
    private final String lastImagePath = "";

    public SharedPref(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public Bitmap getImage(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public boolean getBoolean(String str) {
        return this.sharedPreferences.getBoolean(str, false);
    }

    public boolean isFirstTime(String str) {
        return this.sharedPreferences.getBoolean(str, true);
    }


    public ArrayList<Long> getListLong(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(TextUtils.split(this.sharedPreferences.getString(str, ""), "‚‗‚")));
        ArrayList<Long> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Long.valueOf(Long.parseLong((String) it.next())));
        }
        return arrayList2;
    }

    public String getString(String str) {
        return this.sharedPreferences.getString(str, "");
    }

    public ArrayList<String> getListString(String str) {
        return new ArrayList<>(Arrays.asList(TextUtils.split(this.sharedPreferences.getString(str, ""), "‚‗‚")));
    }


    public ArrayList<Boolean> getListBoolean(String str) {
        ArrayList<String> listString = getListString(str);
        ArrayList<Boolean> arrayList = new ArrayList<>();
        Iterator<String> it = listString.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().equals("true"));
        }
        return arrayList;
    }

    public void putLong(String str, long j) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putLong(str, j).apply();
    }

    public int getInt(String str) {
        return this.sharedPreferences.getInt(str, 0);
    }

    public ArrayList<Integer> getListInt(String str) {
        ArrayList arrayList = new ArrayList(Arrays.asList(TextUtils.split(this.sharedPreferences.getString(str, ""), "‚‗‚")));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(Integer.parseInt((String) it.next())));
        }
        return arrayList2;
    }


    public void putListLong(String str, ArrayList<Long> arrayList) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putString(str, TextUtils.join("‚‗‚", arrayList.toArray(new Long[arrayList.size()]))).apply();
    }

    public void putInt(String str, int i) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putInt(str, i).apply();
    }

    public void putListInt(String str, ArrayList<Integer> arrayList) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putString(str, TextUtils.join("‚‗‚", arrayList.toArray(new Integer[arrayList.size()]))).apply();
    }


    public void putFloat(String str, float f) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putFloat(str, f).apply();
    }

    public void putDouble(String str, double d) {
        checkForNullKey(str);
        putString(str, String.valueOf(d));
    }

    public void putListString(String str, ArrayList<String> arrayList) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putString(str, TextUtils.join("‚‗‚", arrayList.toArray(new String[arrayList.size()]))).apply();
    }

    public void putBoolean(String str, boolean z) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putBoolean(str, z).apply();
    }

    public void putListDouble(String str, ArrayList<Double> arrayList) {
        checkForNullKey(str);
        this.sharedPreferences.edit().putString(str, TextUtils.join("‚‗‚", arrayList.toArray(new Double[arrayList.size()]))).apply();
    }

    public void putString(String str, String str2) {
        checkForNullKey(str);
        checkForNullValue(str2);
        this.sharedPreferences.edit().putString(str, str2).apply();
    }


    public void putListBoolean(String str, ArrayList<Boolean> arrayList) {
        checkForNullKey(str);
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<Boolean> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().booleanValue()) {
                arrayList2.add("true");
            } else {
                arrayList2.add("false");
            }
        }
        putListString(str, arrayList2);
    }

    public void checkForNullKey(String str) {
        Objects.requireNonNull(str);
    }

    public void checkForNullValue(String str) {
        Objects.requireNonNull(str);
    }

    public void remove(String str) {
        this.sharedPreferences.edit().remove(str).apply();
    }

    public boolean deleteImage(String str) {
        return new File(str).delete();
    }

    public void clear() {
        this.sharedPreferences.edit().clear().apply();
    }

    public Map<String, ?> getAll() {
        return this.sharedPreferences.getAll();
    }


}
