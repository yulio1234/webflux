package com.yuli.webflux.lambda;

public class Test1 {
    interface Interface1 {
        int doubleNum(int i);
        default int add(int x,int y){
            return x + y;
        }
    }
    interface Interface2 {
        int doubleNum(int i);
        default int add(int x,int y){
            return x + y;
        }
    }

    /**
     * 解决默认方法重复问题
     */
    interface  Interface3 extends Interface1,Interface2{

        @Override
        default int add(int x, int y) {
            return Interface1.super.add(x,y);
        }
    }
    public static void main(String[] args) {
        Interface1 i1 = (i) -> i*2;
        i1.add(1,2);
        Interface1 i2 = i -> i* 2;

    }
}
