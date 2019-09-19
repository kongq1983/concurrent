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
public class CompletableFutureExceptionDemo {


    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        //阻塞
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
            return "hello";
        }).exceptionally(e-> {
            System.out.println("exception="+e.getMessage());
            return "error";
        });

        String result = cf.getNow("nowReturn");

        stopWatch.stop();

        System.out.printf("result=%s，spent time = %s \n",result,stopWatch.toString());

        TimeUnit.SECONDS.sleep(2);
        cf.cancel(true);

        //等待CompletableFuture执行结束
        cf.join();

        System.out.println("-----------------the end-------------------------");

    }

    public static void printError(Throwable e){
        e.printStackTrace();
//        System.out.println("str="+str);
    }

}
