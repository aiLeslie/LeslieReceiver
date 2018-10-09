package com.leslie.lesliereceiver.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.leslie.lesliereceiver.R;
import com.leslie.lesliereceiver.receiver.MyReceiver;


public class MainActivity extends AppCompatActivity {
    MyReceiver receiver;

    @Override
    protected void onStart() {
        super.onStart();
        addAction();
        receiver.register();
    }
    private void addAction() {
        receiver.addAction(MyReceiver.MSG);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        receiver = new MyReceiver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        receiver.unregister();
    }

    public void btn_onClick(View view) {
        sendBroadcast(editIntent());
    }

    private Intent editIntent() {
        Intent intent = new Intent(MyReceiver.MSG);
        intent.putExtra("INFO", "HELLO");
        return intent;
    }




}
