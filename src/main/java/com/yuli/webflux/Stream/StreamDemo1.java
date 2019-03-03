package com.yuli.webflux.Stream;

import java.util.stream.IntStream;

/**
 * 流的概念，中间，结束
 */
public class StreamDemo1 {
    public static void main(String[] args) {
        int[] ints = {3, 123, 1, 2, 32, 4, 25, 21, 21, 4};
//        System.out.println(IntStream.of(ints).map(SteamDemo1::times1).sum());
        //惰性求值
        System.out.println(IntStream.of(ints).map(StreamDemo1::times1));
    }

    public static int times1(int i) {
        System.out.println(i);
        return i + 1;
    }
}
