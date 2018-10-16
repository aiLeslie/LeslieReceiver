package com.leslie.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.leslie.receiver_annotation.Action;
import com.leslie.receiver_annotation.OnReceive;
import com.leslie.receiver_annotation.Receiver;
import com.leslie.receiver_api.ReceiverBinder;
import com.leslie.receiver_api.receiver.MyReceiver;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Receiver
    MyReceiver receiver;
    @Action
    private static final String MSG_ACTION = "com.leslie.test.MainActivity.MSG_ACTION";

    private TextView textView;


    @Override
    protected void onStart() {
        super.onStart();
        // 注册接收器
        receiver.register();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化
        init();

    }

    private void init() {
        // 绑定接收器
        ReceiverBinder.bind(this);
        // 绑定视图
        bindViews();
    }

    private void bindViews() {
        textView = findViewById(R.id.textView);
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        receiver.unregister();
//    }

    @Override
    protected void onDestroy() {
        // 取消注册接收器
        receiver.unregister();
        super.onDestroy();
    }

    /**
     * 接收广播事件回调
     * @param intent
     */
    @OnReceive(MSG_ACTION)
    public void showMsg(Intent intent) {
        String info = intent.getStringExtra("INFO");
        updateTextView(intent, info);

    }

    /**
     * 更新文本视图
     * @param intent
     * @param msg
     */
    private void updateTextView(Intent intent, String msg) {
        printSplitLine(textView);

        printIntent(textView, intent);

        textView.append(msg);

        printSplitLine(textView);
    }

    /**
     * 在文本视图中打印分割线
     * @param view
     */
    protected void printSplitLine(TextView view) {
        textView.append("\n");
        for (int i = 0; i < 80; i++) {
            view.append("-");
        }
        view.append("\n");
    }

    /**
     * 打印广播信息
     * @param view
     * @param intent
     */
    protected void printIntent(TextView view, Intent intent) {
        view.append(new Date().toLocaleString());
        view.append("\n");
        view.append(intent.toString());
        view.append("\n");
    }
}
