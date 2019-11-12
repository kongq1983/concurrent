package com.kq.concurrent.guava.feture.demo1;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.JdkFutureAdapters;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.*;

/**
 * feture异步执行
 * FetureDemo1
 *
 * @author kq
 * @date 2019-11-12
 */
public class FetureDemo1 {

    static BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,3,TimeUnit.SECONDS,blockingQueue);

    public static void main(String[] args) throws Exception{

        Callable<String> callable1 = ()-> {
            System.out.println("---------------------start callable1-----------");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("---------------------e-n-d callable1-----------");
            return "ok1";
        };

        Callable<String> callable2 = ()-> {
            System.out.println("---------------------start callable2-----------");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("---------------------e-n-d callable2-----------");
            return "ok2";
        };

        ListenableFuture<String> listenableFuture1 = JdkFutureAdapters.listenInPoolThread(threadPoolExecutor.submit(callable1));

        callback(listenableFuture1);

        ListenableFuture<String> listenableFuture2 = JdkFutureAdapters.listenInPoolThread(threadPoolExecutor.submit(callable2));
        callback(listenableFuture2);


        System.out.println("---------------main thread execute -------------------------");

        TimeUnit.SECONDS.sleep(8);
        threadPoolExecutor.shutdown();
    }

    private static void callback(ListenableFuture<String> listenableFuture){
        Futures.addCallback(listenableFuture,new FutureCallback<String>(){

            @Override
            public void onSuccess(String result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        },threadPoolExecutor);
    }

}
