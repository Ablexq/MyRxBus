package com.example.myrxbus.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myrxbus.R;
import com.example.myrxbus.event.RxMessage1;
import com.example.myrxbus.event.RxMessage2;
import com.example.myrxbus.util.TimeUtil;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String time = TimeUtil.stampToDateHms(System.currentTimeMillis());
                        RxMessage2 rxMessage2 = new RxMessage2("发送2时间是：" + time);
                        postEvent(rxMessage2);

                        handler.postDelayed(this, 1000);
                    }
                }, 1000);
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        final TextView textView = (TextView) findViewById(R.id.tv1);
        addSubscription(RxMessage1.class, new Consumer<RxMessage1>() {//订阅
            @SuppressLint("SetTextI18n")
            @Override
            public void accept(RxMessage1 rxMessage) {
                textView.setText("RxMessage1：" + rxMessage.getMsg());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("RxMessage1 throwable=====" + throwable.getMessage());
            }
        });

    }

}
