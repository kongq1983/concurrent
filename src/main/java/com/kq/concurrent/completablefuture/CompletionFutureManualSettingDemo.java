package com.kq.concurrent.completablefuture;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.CompletableFuture;

/**
 * CompletionFutureManualSettingDemo
 * 手动设置返回值
 * @author kq
 * @date 2019-07-29
 */
public class CompletionFutureManualSettingDemo {


    public static void main(String[] args) throws Exception{

        System.out.println(DateUtil.getNowTime()+",start0-------------------------------------");

        //complete   手动指定结果
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        System.out.println(DateUtil.getNowTime()+",start1-------------------------------------");

//        Thread.sleep(2000L);
        future2.complete("the end");    //手动指定结果  直接出发whenComplete

        System.out.println(DateUtil.getNowTime()+",start2-------------------------------------");

        future2.whenComplete((str, throwable) -> System.out.println(DateUtil.getNowTime()+","+str));

        Thread.sleep(5000l);

    }


}
