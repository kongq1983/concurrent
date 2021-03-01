package com.kq.concurrent.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 *
 * https://www.jianshu.com/p/dade70be4edd
 * Created by qikong on 2019/9/20.
 */
public class CompletionFutureDemo3 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2,r->{
           Thread t = new Thread(r);
           t.setDaemon(false);
           return t;
        });


        // 先计算值   然后再把计算好的值*10 饭后输出
//        CompletableFuture.supplyAsync(CompletableFutureWhenCompleteDemo::get,executorService)
//                .thenApply(CompletableFutureWhenCompleteDemo::multiply).whenComplete((v,t)-> {
//            Optional.ofNullable(v).ifPresent(System.out::println);
//        });

        List<Integer> ids = Arrays.asList(1,2,3,4,5);

        List<Double> result = ids.stream().map(i-> CompletableFuture.supplyAsync(()-> queryProduct(i),executorService))
                .map(f-> f.thenApply(CompletableFutureWhenCompleteDemo::multiply))
                .map(CompletableFuture::join).collect(Collectors.toList());

        System.out.println(result);


    }

    public static double queryProduct(int i) {

        double result = i+0.5;
        System.out.println("queryProduct i="+result);
        return result;
    }

}
