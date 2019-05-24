package com.example.myrxbus.activity;

import android.support.v7.app.AppCompatActivity;

import com.example.myrxbus.RxBus;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class BaseActivity extends AppCompatActivity {

    //发送
    protected void postEvent(Object object) {
        RxBus.getRxBus().post(object);
    }

    //订阅
    protected <M> void addSubscription(Class<M> eventType, Consumer<M> action, Consumer<Throwable> error) {
        Disposable disposable = RxBus.getRxBus().doSubscribe(eventType, action, error);
        RxBus.getRxBus().addSubscription(this, disposable);
    }

    //取消订阅
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getRxBus().unSubscribe(this);
    }

}
