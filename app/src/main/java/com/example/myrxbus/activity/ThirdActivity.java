package com.example.myrxbus.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myrxbus.R;
import com.example.myrxbus.event.RxMessage1;
import com.example.myrxbus.event.RxMessage2;

import io.reactivex.functions.Consumer;

public class ThirdActivity extends BaseActivity {
    private static final String TAG = ThirdActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        final TextView textView = (TextView) findViewById(R.id.tv1);
        addSubscription(RxMessage1.class, new Consumer<RxMessage1>() {//订阅
            @SuppressLint("SetTextI18n")
            @Override
            public void accept(RxMessage1 rxMessage) {
                Log.e(TAG, "accept1" + rxMessage.getMsg());

                textView.setText("RxMessage1：" + rxMessage.getMsg());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("RxMessage1 throwable=====" + throwable.getMessage());
            }
        });

        final TextView textView2 = (TextView) findViewById(R.id.tv2);
        addSubscription(RxMessage2.class, new Consumer<RxMessage2>() {//订阅
            @SuppressLint("SetTextI18n")
            @Override
            public void accept(RxMessage2 rxMessage) {
                Log.e(TAG, "accept2" + rxMessage.getMsg());

                textView2.setText("RxMessage2：" + rxMessage.getMsg());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("RxMessage2 throwable=====" + throwable.getMessage());
            }
        });


    }

}
