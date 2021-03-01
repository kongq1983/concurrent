package com.kq.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureRunAsync
 *
 * @author kq
 * @date 2019-09-19
 */
public class CompletableFutureRunAsync {

    public static void main(String[] args) throws Exception{
        //接收参数是Runnable 无结果返回值
        CompletableFuture<Void> f = CompletableFuture.runAsync(()-> System.out.println("execute it."));

        f.get();

    }

}
