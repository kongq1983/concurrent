package com.kq.concurrent.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFutureDemo1
 *
 * @author kq
 * @date 2019-06-11
 */
public class CompletableFutureDemo1 {

    public static void main(String[] args) {

        CompletableFuture.supplyAsync(()->"Hello").thenApply(s->s+"World")
                .thenApply(String::toUpperCase).thenCombine(CompletableFuture.completedFuture("Java"),(s1,s2)->s1+s2)
                .thenAccept(System.out::println);

    }

}
