package com.mutall.billviewer.Util;

import android.telephony.SmsManager;
import android.util.Log;

import java.util.logging.Handler;

public class SmsThread extends Thread {
    private Handler handler;
    private static final String TAG = "SmsThread";
    @Override
    public void run() {
        super.run();
        Log.d(TAG, "run: start thread");
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage("0716276879", null, "hello world", null, null);
    }
}
