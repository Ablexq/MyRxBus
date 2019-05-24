package com.example.myrxbus.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myrxbus.R;
import com.example.myrxbus.event.RxMessage1;
import com.example.myrxbus.event.RxMessage2;
import com.example.myrxbus.util.TimeUtil;

import io.reactivex.functions.Consumer;


public class SecondActivity extends BaseActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final TextView textView = (TextView) findViewById(R.id.tv1);
        addSubscription(RxMessage2.class, new Consumer<RxMessage2>() {//订阅
            @SuppressLint("SetTextI18n")
            @Override
            public void accept(RxMessage2 rxMessage) {
                Log.e(TAG, "accept" + rxMessage.getMsg());

                textView.setText("RxMessage2：" + rxMessage.getMsg());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("RxMessage2 throwable=====" + throwable.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                String time = TimeUtil.stampToDateHms(System.currentTimeMillis());
                RxMessage1 rxMessage = new RxMessage1("发送1时间是：" + time);
                postEvent(rxMessage);
                break;

            case R.id.btn2:
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
                break;
        }
    }
}
