
[基于Rxrelay的RxBus](https://blog.csdn.net/CherryBean/article/details/78899646)

[自定义RxBus + RxRelay](https://blog.csdn.net/qq_39507260/article/details/84996688)

[推荐：RxJava2 系列-3：使用 Subject](https://www.jianshu.com/p/6c6ca95ec27b)

[]()

[]()

# 背压：

``` 
描述：
对于异步订阅关系，存在 被观察者发送事件速度 与观察者接收事件速度 不匹配的情况
发送 & 接收事件速度 = 单位时间内 发送&接收事件的数量
大多数情况，主要是 被观察者发送事件速度 ＞ 观察者接收事件速度

影响：
被观察者 发送事件速度太快，而观察者 来不及接收所有事件，
从而导致观察者无法及时响应 / 处理所有发送过来事件的问题，最终导致缓存区溢出、事件丢失 & OOM
```
推荐阅读：

[Android RxJava：一文带你全面了解 背压策略](https://blog.csdn.net/carson_ho/article/details/79081407)

