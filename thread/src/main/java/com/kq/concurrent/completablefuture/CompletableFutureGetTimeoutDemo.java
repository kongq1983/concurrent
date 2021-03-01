package com.kq.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureGetTimeoutDemo
 *
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureGetTimeoutDemo {

    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //设置超时3s
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).get(3, TimeUnit.SECONDS);

        // 会抛异常
        //Exception in thread "main" java.util.concurrent.TimeoutException
        //	at java.util.concurrent.CompletableFuture.timedGet(CompletableFuture.java:1771)
        //	at java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1915)
        //	at com.kq.concurrent.completablefuture.CompletableFutureGetTimeoutDemo.main(CompletableFutureGetTimeoutDemo.java:29)

        stopWatch.stop();
        System.out.printf("result=%s，spent time = %s",result,stopWatch.toString());

    }

}
