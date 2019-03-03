package com.yuli.webflux.lambda;

import java.text.DecimalFormat;
import java.util.function.Consumer;
import java.util.function.Function;

class MyMoney{
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }
    public String printMoney(Function<Integer,String> moneyFormat){
        return moneyFormat.apply(this.money);
    }
}

public class MoneyDemo {
    public static void main(String[] args) {
        MyMoney myMoney = new MyMoney(99999);
        Function<Integer,String> function = new DecimalFormat("#.###")::format;
        String money = myMoney.printMoney(function.andThen(s -> "人民币"+s));
        System.out.println(money);
    }
}
