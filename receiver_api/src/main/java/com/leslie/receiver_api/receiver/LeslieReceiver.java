package com.leslie.receiver_api.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

public abstract class LeslieReceiver extends BroadcastReceiver {
    protected Context mContext;
    private IntentFilter filter = new IntentFilter();

    public Context getContext() {
        return mContext;
    }


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