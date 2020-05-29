package com.kq.concurrent.executorservice;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {


    static ScheduledExecutorService executorService =  new ScheduledThreadPoolExecutor(3, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("scheduled.demo.thread");
            return thread;
        }
    });

    public static void main(String[] args) throws Exception{
        System.out.println(Thread.currentThread()+" start time = "+DateUtil.getNowTime());
//        schedule();
        scheduleWithFixedDelay();
        TimeUnit.SECONDS.sleep(60);
    }

    public static void scheduleWithFixedDelay(){
        executorService.scheduleWithFixedDelay(()->{
            String now = DateUtil.getNowTime();
            System.out.println(Thread.currentThread()+" scheduleWithFixedDelay time = "+now);
        },3,5,TimeUnit.SECONDS);
    }


    public static void schedule(){

//        executorService.schedule(()->{
//            String now = DateUtil.getNowTime();
//            System.out.println(Thread.currentThread()+" execute time = "+now);
//        },5, TimeUnit.SECONDS);

        executorService.schedule(()->{
            String now = DateUtil.getNowTime();
            System.out.println(Thread.currentThread()+" execute time = "+now);
        },5000, TimeUnit.MILLISECONDS);

    }

}
