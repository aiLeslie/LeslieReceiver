package com.leslie.lesliereceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public abstract class LeslieReceiver extends BroadcastReceiver{
    private Context mContext;
    private IntentFilter filter = new IntentFilter();


    public LeslieReceiver(Context mContext) {
        this.mContext = mContext;
    }

    public void addAction(String action) {
        filter.addAction(action);
    }

    public void register() {
        mContext.registerReceiver(this, filter);
    }

    public void unregister() {
        mContext.unregisterReceiver(this);
    }
}