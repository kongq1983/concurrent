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
//        scheduleWithFixedDelay();
        scheduleAtFixedRate();
        TimeUnit.SECONDS.sleep(60);
    }

    /**
     * 上一次结束之后 再等侯delay秒
     */
    public static void scheduleWithFixedDelay(){
        executorService.scheduleWithFixedDelay(()->{
            String now = DateUtil.getNowTime();
            System.out.println(Thread.currentThread()+" scheduleWithFixedDelay time = "+now);
        },3,5,TimeUnit.SECONDS);
    }


    /**
     * 固定频率 上一次开始时间开始算
     * 如果频率时间到了  本次任务还没结束，则等到本次任务结束后，再立即开始下一次任务
     */
    public static void scheduleAtFixedRate(){
        executorService.scheduleAtFixedRate(()->{
            String now = DateUtil.getNowTime();
            System.out.println(Thread.currentThread()+" scheduleAtFixedRate time = "+now);

            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }

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
