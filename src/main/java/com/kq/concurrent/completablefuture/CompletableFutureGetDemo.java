package com.kq.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureGetDemo
 *
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureGetDemo {


    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        //阻塞
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).get();

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s",result,stopWatch.toString());


    }

}
