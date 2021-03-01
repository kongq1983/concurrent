package com.kq.concurrent.threadlocal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author kq
 * @date 2021-01-19 10:48
 * @since 2020-0630
 */
public class ThreadPoolInvokeAllDemo {

    static BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,5,60, TimeUnit.SECONDS,blockingQueue);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<String> runnable1 = ()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }

            return "runnable1";

        };

        Callable<String> runnable2 = ()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (Exception e){
                e.printStackTrace();
            }

            return "runnable2";

        };

        Callable<String> runnable3 = ()->{
            try {
                TimeUnit.SECONDS.sleep(8);
            }catch (Exception e){
                e.printStackTrace();
            }

            return "runnable3";

        };

        Collection<Callable<String>> list = new ArrayList<>();
        list.add(runnable1);
        list.add(runnable2);
        list.add(runnable3);

        long start = System.currentTimeMillis();
//        String result = threadPoolExecutor.invokeAny(list);
        List<Future<String>> resultList = threadPoolExecutor.invokeAll(list);
        long end = System.currentTimeMillis();
        System.out.println("result="+resultList+", spent time="+(end-start));

        for(Future<String> f : resultList) {
            String result = f.get();
            System.out.println("result="+result);
        }

        TimeUnit.SECONDS.sleep(20);
        threadPoolExecutor.shutdown();

    }

}
