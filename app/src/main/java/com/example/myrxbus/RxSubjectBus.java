package com.example.myrxbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxSubjectBus {
    private static volatile RxSubjectBus rxBus;
    //Relay: 在onComplete或者onError后不会终止事件订阅关系
    //ReplayRelay：支持粘性事件，可接收全部消息
    private final Subject<Object> bus;
    //避免内存泄漏
    private Map<String, CompositeDisposable> disposableMap;

    private RxSubjectBus() {
        bus = PublishSubject.create().toSerialized();
        disposableMap = new ConcurrentHashMap<>();
    }

    public static RxSubjectBus getRxBus() {
        if (rxBus == null) {
            synchronized (RxSubjectBus.class) {
                if (rxBus == null) {
                    rxBus = new RxSubjectBus();
                }
            }
        }
        return rxBus;
    }

    //Flowable解决背压
    private <T> Flowable<T> getObservable(Class<T> type) {
        return bus.toFlowable(BackpressureStrategy.BUFFER).ofType(type);
    }

    public void addSubscription(Object o, Disposable disposable) {
        String key = o.getClass().getName();
        CompositeDisposable compositeDisposable = disposableMap.get(key);
        if (compositeDisposable != null) {
            compositeDisposable.add(disposable);
        } else {
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            disposableMap.put(key, disposables);
        }
    }

    /*
     * 取消订阅
     * */
    public void unSubscribe(Object o) {
        String key = o.getClass().getName();
        if (!disposableMap.containsKey(key)) {
            return;
        }
        CompositeDisposable compositeDisposable = disposableMap.get(key);
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        disposableMap.remove(key);
    }

    /*
     * 发送事件
     * */
    public void post(Object o) {
        bus.onNext(o);
    }

    /*
     * 订阅
     * */
    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    /*
     * 判断是否有观察者
     * */
    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
