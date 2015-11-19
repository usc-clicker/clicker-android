package edu.usc.clicker.util;

import android.util.Log;

import edu.usc.clicker.BuildConfig;

public class ClickerLog {
    private static final String DEFAULT_TAG = "Clicker";

    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(DEFAULT_TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(DEFAULT_TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(DEFAULT_TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(DEFAULT_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(DEFAULT_TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

    public static void wtf(String msg) {
        if (BuildConfig.DEBUG) {
            Log.wtf(DEFAULT_TAG, msg);
        }
    }

    public static void wtf(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.wtf(tag, msg);
        }
    }
}
