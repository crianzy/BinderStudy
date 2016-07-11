package com.czy.binderstudy;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by chenzhiyong on 16/7/11.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: process = " + Process.myPid());
    }
}
