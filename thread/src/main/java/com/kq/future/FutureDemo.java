package com.kq.future;



import javax.annotation.Nullable;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2022-10-28 10:05
 * @since 2020-0630
 */
public class FutureDemo {

    private static ExecutorService threadPoolExecutor = (ExecutorService) Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        Callable r = ()->{
            Random rand = new Random();
            String result =  String.valueOf(rand.nextInt());
            TimeUnit.SECONDS.sleep(3);
            System.out.println("result="+result);
            return result;
        };

        ListenableFuture<String> listenableFuture = JdkFutureAdapters.listenInPoolThread(threadPoolExecutor.submit(r));

        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(@Nullable String result) {
                System.out.println("onSuccess result = "+result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("onFailure result = "+t);
            }
        }, threadPoolExecutor);


    }

}
