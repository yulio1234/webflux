package com.yuli.webflux.Stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 流的中间操作
 * 无状态：map,flatMap,filter,peek
 *
 *
 *有状态操作,distinct,sorted,limit,skip
 *
 */
public class StreamDemo3 {
    public static void main(String[] args) {
        String string = "my name is 007";
        Stream.of(string.split(" ")).map(s -> s.length()).filter(l->l > 2).forEach(System.out::println);

        //flatMap
        //boxed进行装箱，因为IntStream不是Stream的子类
        Stream.of(string.split(" ")).flatMap(s -> s.chars().boxed()).map(i->(char)i.intValue()).forEach(System.out::println);
        //peek
        Stream.of(string.split(" ")).peek(System.out::println).forEach(System.out::println);
        //limit
        new Random().ints().filter(i-> i%2==0).limit(50).forEach(System.out::println);
    }
}
