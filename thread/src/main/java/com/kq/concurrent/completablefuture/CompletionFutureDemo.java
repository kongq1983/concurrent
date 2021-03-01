package com.kq.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * CompletionFutureDemo
 *
 * @author kq
 * @date 2019-05-25
 */
public class CompletionFutureDemo {

    public static void main(String[] args) throws Exception{

        //whenComplete  完成时 触发的事件
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        future1.whenComplete(
                (str, throwable) -> {
                    System.out.println(str);
                }
        );



        //complete   手动指定结果
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        Thread.sleep(5000L);
        future2.complete("fsdf");    //手动指定结果

        future2.whenComplete((str, throwable) -> System.out.println(str));



        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2);

        future3.whenComplete((str, throwable) -> System.out.println(str));
    }

}
