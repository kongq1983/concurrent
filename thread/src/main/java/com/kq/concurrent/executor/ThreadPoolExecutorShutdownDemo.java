package com.kq.concurrent.executor;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 等現有线程执行完，然后结束
 * Created by qikong on 2020/5/28.
 */
public class ThreadPoolExecutorShutdownDemo {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,
            5, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) {

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

        new MyThread().start();
        threadPoolExecutor.shutdown();


        System.out.println(DateUtil.getNowTime()+", "+Thread.currentThread().getName()+" is end.===========");



    }

    static class MyThread extends Thread {

        public void run(){
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }

            //
            try {  // 由于已经shutdown了 ，这个时候是放不进来的，会抛异常
                threadPoolExecutor.execute(()->{
                    System.out.println("myThread execute-------------");
                });
            }catch (Exception e){
                System.out.println("放入线程池报错! e="+e);
            }
        }

    }


}
