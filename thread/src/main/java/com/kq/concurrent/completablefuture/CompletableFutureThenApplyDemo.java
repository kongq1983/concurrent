package com.kq.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureThenApplyDemo
 * 返回一个新的CompletionStage，当此阶段正常完成时，
 * 将以该阶段的结果作为所提供函数的参数执行
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureThenApplyDemo {

    public static void main(String[] args) throws Exception{
        CompletableFuture<String> f = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("--------------------------");
            return "a";
        }).thenApply(a->{
            System.out.println("**************************");
            return a+"b";
        });

        String result = f.get();
        System.out.println("result="+result);
    }

}
