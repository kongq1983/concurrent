package com.kq.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureThenApplyDemo
 * 返回一个新的CompletionStage，当该阶段正常完成时，
 * 将使用此阶段的默认异步执行工具执行此阶段的结果作为所提供函数的参数
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureThenApplyAsyncDemo {

    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture<String> f = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"--------------------------");
            return "a";
        }).thenApplyAsync(a->{ //异步执行
            System.out.println(Thread.currentThread().getName()+"**************************");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return a+"b";
        }).thenApplyAsync(a->{ //异步执行
            System.out.println(Thread.currentThread().getName()+"++++++++++++++++++++++++++");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return a+"c";
        });

        f.join();
//                String result = f.get();
//        System.out.println("result="+result);

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s \n",f.get(),stopWatch.toString());


        TimeUnit.SECONDS.sleep(8);
    }

}
