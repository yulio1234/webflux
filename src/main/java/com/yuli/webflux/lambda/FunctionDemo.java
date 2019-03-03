package com.yuli.webflux.lambda;

import lombok.Data;

import java.util.function.*;
class Dog{
      String name;
     int food = 10;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public static void bark(Dog dog){
        System.out.println(dog.name+"叫了");
    }

    /**
     * JDK默认会把参数传入第一个参数，参数名为this
     * @param i
     * @return
     */
    public int eat(Dog this,int i){
        System.out.println("吃了：" +i+"斤");
        return food = food-i;
    }

}
/**
 * 函数式接口例子
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //断言函数接口
        Predicate<Integer> predicate = i->i>0;
        System.out.println(predicate.test(-10));
        //消费类型函数
        Consumer<String> consumer = System.out::println;
        consumer.accept("aaa");
        //静态方法函数
        Dog dog = new Dog("哈巴狗");
        Consumer<Dog> dogConsumer = Dog::bark;
        //一元函数,入口和出口一样
        Function<Integer,Integer> function = dog::eat;
        UnaryOperator<Integer> unaryOperator = dog::eat;
        IntUnaryOperator intUnaryOperator = dog::eat;
        System.out.println(unaryOperator.apply(1));

        //使用类型来引用方法
        BiFunction<Dog,Integer,Integer> biFunction = Dog::eat;
        System.out.println(biFunction.apply(dog, 5));

        //构造方法函数引用,没有参数
        Supplier<Dog> supplier = Dog::new;

        Function<String,Dog> function1 = Dog::new;
        FunctionDemo.edit(dog);
        System.out.println(dog.name);
    }
    public static void edit(Dog dog){
        dog=null;
    }

}
