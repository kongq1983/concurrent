package com.kq.concurrent.guava.feture.demo1;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 *
 * CompletableFutureDemo
 *
 * @author kq
 * @date 2019-11-12
 */
public class ASyncCompletableFutureDemo {

    static BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,3, TimeUnit.SECONDS,blockingQueue);

    public static void main(String[] args) throws Exception{

        Supplier<String> callable1 = ()-> {
            String result = "fail";
            System.out.println("---------------------start callable1-----------");
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("---------------------start callable2-----------");
            if(result.equals("fail")){
                System.out.println("---------------------e-n-d callable1-----------");
                throw new RuntimeException("other exception");
            }
            System.out.println("---------------------start callable3-----------");
            return "ok1";
        };

        CompletableFuture<String> future =
                CompletableFuture
                        .supplyAsync(callable1,threadPoolExecutor)
                        .exceptionally(e-> {
                            System.out.println("exception="+e.getMessage());
                            return "error";
                        });

        System.out.println("-----------------end-------------------");

        TimeUnit.SECONDS.sleep(10);
        threadPoolExecutor.shutdown();

    }

}
