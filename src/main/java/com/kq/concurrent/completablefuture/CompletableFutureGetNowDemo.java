package com.kq.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureGetDemo
 *
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureGetNowDemo {


    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //阻塞
        String result1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("---------------------------");
            return "hello";
        }).getNow("nowReturn");

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s \n",result1,stopWatch.toString());


        stopWatch.reset();
        stopWatch.start();
        //阻塞
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).getNow("nowReturn");

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s \n",result,stopWatch.toString());

        TimeUnit.SECONDS.sleep(8);

    }

}
