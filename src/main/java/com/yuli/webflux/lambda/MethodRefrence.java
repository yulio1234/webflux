package com.yuli.webflux.lambda;

import java.util.function.Consumer;

public class MethodRefrence {
    public static void main(String[] args) {
        //方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("aaa");
        //静态方法引用
    }
}
