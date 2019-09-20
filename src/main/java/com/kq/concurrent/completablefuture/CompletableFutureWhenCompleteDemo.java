package com.kq.concurrent.completablefuture;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by qikong on 2019/9/20.
 */
public class CompletableFutureWhenCompleteDemo {

    public static void main(String[] args) throws Exception{

        CompletableFuture<Double> f = new CompletableFuture<>();
        new Thread(()->{
            double value = get();
            f.complete(value);
        }).start();

        System.out.println("=====================================");

//        f.whenComplete((v,t)-> {
//            Optional.ofNullable(v).ifPresent(System.out::println);
//            Optional.ofNullable(t).ifPresent(x-> x.printStackTrace());
//        });

        // 异步
        f.whenCompleteAsync((v,t)-> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x-> x.printStackTrace());
        });


        System.out.println("------------------------------------");

//        TimeUnit.SECONDS.sleep(8);


    }

    public static double get() {

        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }

        double d = new Random().nextDouble();
        System.out.println("d1="+d);
        return d;
    }

    public static double multiply(double value) {

        try {
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }

        double d = value*10;
        System.out.println("d2="+d);
        return d;
    }


}
