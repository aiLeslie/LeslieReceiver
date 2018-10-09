package com.leslie.transmission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String MSG_ACTION = "com.leslie.test.MainActivity.MSG_ACTION";

    @BindView(R.id.editText)
    EditText editText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.button)
    protected void sendMsg() {
        String info = editText.getText().toString();
        sendBroadcast(editIntent(info));

    }

    private Intent editIntent(String info) {
        Intent intent = new Intent(MSG_ACTION);
        intent.putExtra("INFO", info);
        return intent;
    }
}
