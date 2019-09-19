package com.kq.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFutureSupplyAsync
 *
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureSupplyAsync {

    public static void main(String[] args) throws Exception{
        CompletableFuture<String> f = CompletableFuture.supplyAsync(()-> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "ok";

        });

        System.out.println("----------------------------");

        f.join();

        System.out.println(f.get());

    }

}
