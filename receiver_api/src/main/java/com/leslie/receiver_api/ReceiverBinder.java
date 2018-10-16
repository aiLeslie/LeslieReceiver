package com.leslie.receiver_api;

import android.app.Activity;

import com.leslie.receiver_annotation.Action;
import com.leslie.receiver_annotation.OnReceive;
import com.leslie.receiver_annotation.Receiver;
import com.leslie.receiver_api.receiver.MyReceiver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReceiverBinder {

    public static <A extends Activity> void bind(A activity) {
        MyReceiver receiver = null;
        if ((receiver = isExistsReceiver(activity)) == null) {
            return;
        }

        initReceiver(receiver);

    }

    private static <A extends Activity> MyReceiver isExistsReceiver(A activity) {

        MyReceiver receiver = null;

        Class<? extends Activity> aClass = activity.getClass();

        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Receiver.class)) {

                try {
                    receiver = new MyReceiver(activity);
                    // 注入Receiver对象
                    field.setAccessible(true);
                    field.set(activity, receiver);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return receiver;
    }

    private static <A extends Activity> void initReceiver(MyReceiver receiver) {

        A activity = (A) receiver.getContext();
        Class<? extends Activity> aClass = activity.getClass();

        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Action.class)) {
                try {
                    field.setAccessible(true);
                    receiver.addAction((String) field.get(activity));
                    receiver.addMethod((String) field.get(activity), null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(OnReceive.class)) {
                method.setAccessible(true);
                OnReceive onReceive = method.getAnnotation(OnReceive.class);
                receiver.addMethod(onReceive.value(), method);
            }
        }

    }





}
