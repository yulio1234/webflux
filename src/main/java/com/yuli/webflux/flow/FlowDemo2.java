package com.yuli.webflux.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Processor，需要继承SubmissionPublisher并实现Processor接口
 * 输入数据源Integer，过滤掉小于0的，然后转换成字符串发布出去
 */
public class FlowDemo2 extends SubmissionPublisher<String> implements Flow.Processor<Integer,String> {
    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer item) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    public static void main(String[] args) {
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //定义处理器，对数据进行过滤，并转换为String类型

    }
}
