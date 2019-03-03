package com.yuli.webflux.Stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 终止流
 * 非短路操作，等待流执行玩
 * 短路操作，不需要等待流执行完
 */
public class StreamDemo4 {
    public static void main(String[] args) {
        String string = "my name is 007";
        //使用并行流
        string.chars().parallel().map(i->(char)i).forEach(System.out::println);
        //保证顺序
        string.chars().parallel().map(i->(char)i).forEachOrdered(System.out::println);
        //collect收集器
        List<String> list = Stream.of(string.split(" ")).collect(Collectors.toList());
        //reduce

    }
}
