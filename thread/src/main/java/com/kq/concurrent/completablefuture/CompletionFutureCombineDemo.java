package com.kq.concurrent.completablefuture;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.CompletableFuture;

/**
 * CompletionFutureCombineDemo
 *
 * @author kq
 * @date 2019-07-29
 */
public class CompletionFutureCombineDemo {

    public static void main(String[] args) throws Exception{

        System.out.println(DateUtil.getNowTime()+",start0-------------------------------------");

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(DateUtil.getNowTime()+",start1-------------------------------------");
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(DateUtil.getNowTime()+",start2-------------------------------------");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2);

        // start1 和 start2 同时执行
        // start1需要2.5s   start2需要3s
        // 所以最多花费 start2的3s
        // 总共花费3s

        future3.whenComplete((str, throwable) ->  System.out.println(DateUtil.getNowTime()+","+str));


        Thread.sleep(10000l);

    }

}
