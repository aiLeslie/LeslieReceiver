package com.leslie.receiver_api.receiver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


public class MyReceiver extends LeslieReceiver {
    public HashMap<String, Method> methods = new HashMap<>();

    public MyReceiver(Context mContext) {
        super(mContext);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        handleIntent(intent);

    }

    /**
     * 处理Intent
     *
     * @param intent
     */
    private void handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if (!methods.containsKey(action)) {
            return;
        }


        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Method method = methods.get(action);
                try {
                    method.invoke(mContext, new Object[]{intent});
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public Method addMethod(String action, Method method) {
        return methods.put(action, method);
    }


}
