package com.kq.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureDemo1
 * CompletionStage代表异步计算过程中的某一个阶段，一个阶段完成以后可能会触发另外一个阶段
 * 一个阶段的计算执行可以是一个Function，Consumer或者Runnable
 * 比如：
 * stage.thenApply(x -> square(x)).thenAccept(x -> System.out.print(x)).thenRun(() -> System.out.println())
 * @author kq
 * @date 2019-06-11
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) throws Exception{

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        // 完成 回调
        future1.whenComplete(
            (str, throwable) -> {
                System.out.println(str);
            }
        );

        //
        CompletableFuture.supplyAsync(()->1)
                .thenApply(i->i+8)
                .thenApply(i-> i*i)
                .whenComplete((i,e)-> System.out.println("i*i="+i));

        CompletableFuture.supplyAsync(()->"Hello").thenApply(s->s+"World")
                .thenApply(String::toUpperCase).thenCombine(CompletableFuture.completedFuture("Java"),(s1,s2)->s1+s2)
                .thenAccept(System.out::println);

        TimeUnit.SECONDS.sleep(8l);



    }

}
