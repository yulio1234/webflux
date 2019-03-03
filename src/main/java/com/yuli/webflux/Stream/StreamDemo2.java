package com.yuli.webflux.Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 流的创建
 *
 */
public class StreamDemo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //同步流
        list.stream();
        //并行流
        list.parallelStream();

        //从数组创建
        Arrays.stream(new int[]{1,2,3,4});
        //创建数字流
        IntStream.of(1,2,3,4,5);
        IntStream.rangeClosed(1,10);
        //创建无限流
        IntStream intStream = new Random().ints().limit(10);
        Random random = new Random();
        Stream.generate(random::nextInt).limit(20);
    }
}
