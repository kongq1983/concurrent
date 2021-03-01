package com.kq.concurrent.completablefuture;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureAllOfDemo
 * 所有的future都完成
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureAllOfDemo {


    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

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

        CompletableFuture<Void> future = CompletableFuture.allOf(f1,f2);

        future.get();

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s \n","",stopWatch.toString());

    }


}
