package com.yuli.webflux.lambda;

import java.util.function.Function;

/**
 * 级联表达式和柯里化
 */
public class CuuryDemo {
    public static void main(String[] args) {
        Function<Integer,Function<Integer,Integer>> fun = x->y-> x + y;
        System.out.println(fun.apply(1).apply(2));
    }
}
