package com.yuli.webflux.Stream;

import java.util.stream.IntStream;

/**
 * 并行流
 */
public class StreamDemo5 {
    public static void main(String[] args) {
//        IntStream.rangeClosed(1,100).peek(StreamDemo5::debug).count();
        IntStream.rangeClosed(1,100).parallel().peek(StreamDemo5::debug).count();
        //先并行后串行，以最后的为准
        IntStream.rangeClosed(1,100).parallel().peek(StreamDemo5::debug).sequential().peek(StreamDemo5::debug2).count();



    }
    public static void debug(int i){
        System.out.println(i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void debug2(int i){
        System.out.println(i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
