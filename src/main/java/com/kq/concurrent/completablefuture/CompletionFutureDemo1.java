package com.kq.concurrent.completablefuture;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.CompletableFuture;

/**
 * CompletionFutureDemo1
 *
 * @author kq
 * @date 2019-07-29
 */
public class CompletionFutureDemo1 {

    public static void main(String[] args) throws Exception {

        System.out.println(DateUtil.getNowTime()+",start-------------------------------------");

        //whenComplete  完成时 触发的事件
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        future1.whenComplete(
                (str, throwable) -> {
                    System.out.println(DateUtil.getNowTime()+","+str);
                }
        );

        Thread.sleep(10000l);

    }



}
