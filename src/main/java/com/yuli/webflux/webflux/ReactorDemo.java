package com.yuli.webflux.webflux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class ReactorDemo {
    public static void main(String[] args) {
        //reactor = jdk8 stream+jdk9 reactive string
        //Mono 0-1个元素
        //Flux 0-n个元素
        String[] strs = {"1","2","3"};
        Subscriber<Integer> subscriber = new Subscriber<>() {
            private Subscription subscription;
            /**
             * 建立订阅关系时触发
             * @param subscription
             */
            @Override
            public void onSubscribe(Subscription subscription) {
                this.subscription = subscription;
                //请求一个数据
                this.subscription.request(1);
            }
            /**
             * 接受一个数据，并处理
             * @param item
             */
            @Override
            public void onNext(Integer item) {
                System.out.println("接收到一个数据" + item);
                //处理完数据，并接受一个
                this.subscription.request(1);
                //或者已经完成处理，告诉发布者不接受数据了
//                this.subscription.cancel();
            }

            /**
             * onNext出错了
             * @param throwable
             */
            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("数据都处理完了");
            }
        };
        //jdk8的stream
        Flux.fromArray(strs).map(s -> Integer.parseInt(s))
                //jdk9的响应式流
                .subscribe(subscriber);
    }
}
