package com.mutall.billviewer.Util;

import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.List;

public class SmsThread extends Thread {
    private Handler handler;
    private static final String TAG = "SmsThread";
    private List<String> nums;
    public SmsThread(List<String> nums, Handler handler) {
        this.nums = nums;
        this.handler = handler;
        Log.d(TAG, "SmsThread: "+nums);
    }

    @Override
    public synchronized void run() {
        super.run();
        Log.d(TAG, "run: start thread");
        SmsManager manager = SmsManager.getDefault();

        for (int i=0; i<nums.size(); i++){
            manager.sendTextMessage(Constants.KPLC, null, nums.get(i), null, null);
            handler.obtainMessage(Constants.HANDLER, nums.get(i)).sendToTarget();
            try {
                this.wait(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
