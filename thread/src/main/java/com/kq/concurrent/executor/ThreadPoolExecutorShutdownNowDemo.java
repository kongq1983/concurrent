package com.kq.concurrent.executor;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by qikong on 2020/5/28.
 */
public class ThreadPoolExecutorShutdownNowDemo {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,
            5, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) throws Exception{

        AtomicInteger atomicInteger = new AtomicInteger(1);

        Runnable runnable = ()-> {
            System.out.println(Thread.currentThread().getName()+" is run.");

            try {
                TimeUnit.SECONDS.sleep(atomicInteger.incrementAndGet());
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is end.");

        };

        System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is start.===========");

        for(int i=0;i<10;i++) {
            threadPoolExecutor.execute(runnable);
        }

        TimeUnit.SECONDS.sleep(1);

        threadPoolExecutor.shutdownNow(); //shutdownNow

        System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is end.===========");



    }


}
