package com.yuli.webflux.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class FlowDemo {
    public static void main(String[] args) throws InterruptedException {
        //1定义发布者，发布的数据类型是Integer
        //直接使用jdk自带的SubmissionPublisher，它实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        //2.定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;
            /**
             * 建立订阅关系时触发
             * @param subscription
             */
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
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
        //3.发布者和订阅者建立订阅关系
        publisher.subscribe(subscriber);
        //4.生产数据，并发布
        int data = 1233;
        publisher.submit(data);
        //需要确保被关闭
        publisher.close();
        //主线程延迟停止，否则没消费数据就被关闭了
        Thread.currentThread().join(100);
    }
}
