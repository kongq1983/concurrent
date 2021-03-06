package com.kq.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureAllOfDemo
 * 只要一个future完成，就返回
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureAnyOfDemo {


    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 提交一个执行的任务,有结果返回值
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f1";

        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";

        });

        CompletableFuture<Object> future = CompletableFuture.anyOf(f1,f2);

        Object reesult = future.get();

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s \n",reesult,stopWatch.toString());

    }


}
