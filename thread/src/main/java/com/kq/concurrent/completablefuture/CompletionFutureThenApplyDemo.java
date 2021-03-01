package com.kq.concurrent.completablefuture;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.CompletableFuture;

/**
 * CompletionFutureThenApplyDemo
 * thenApply相当于回调函数
 *
 * @author kq
 * @date 2019-07-29
 */
public class CompletionFutureThenApplyDemo {

    public static void main(String[] args) throws Exception {

        CompletableFuture.supplyAsync(() -> 1).thenApply(i -> i + 1).thenApply(i -> i * i)
                .whenComplete((r, e) -> System.out.println(r));

        CompletableFuture.supplyAsync(() -> 1).thenApplyAsync(i -> i + 1).thenApplyAsync(i -> i * i)
                .whenComplete((r, e) -> System.out.println(r));

        System.out.println(DateUtil.getNowTime() + ",start-------------------------------------");

        CompletableFuture.supplyAsync(() -> 1).thenApply((i) -> {
            try {
                System.out.println(DateUtil.getNowTime() + ",start1-------------------------------------");
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(DateUtil.getNowTime() + ",start11-------------------------------------");
            return i + 1;
        }).thenApply((i) -> {
            try {
                System.out.println(DateUtil.getNowTime() + ",start2-------------------------------------");
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(DateUtil.getNowTime() + ",start22-------------------------------------");
            return i * i;
        })
                .whenComplete((r, e) -> {
                    System.out.println(DateUtil.getNowTime() + ",complete-------------------------------------");
                    System.out.println("result="+r);
                });

        Thread.sleep(10000l);
    }



}
