package com.leslie.lesliereceiver.receiver;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class MyReceiver extends LeslieReceiver {
    public static final String MSG = "msg" ;

    public MyReceiver(Context mContext) {
        super(mContext);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("INFO"), Toast.LENGTH_SHORT).show();
    }
}
