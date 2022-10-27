package com.kq.thread;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @author kq
 * @date 2022-10-27 9:55
 * @since 2020-0630
 */
public class MyFutureTaskDemo {

    static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue(10);

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,3,TimeUnit.SECONDS,workQueue);


    public static void main(String[] args) throws Exception{

        Runnable r2 = () ->{
            System.out.println(Thread.currentThread().getName()+" is r2 start.");


            try {
                TimeUnit.SECONDS.sleep(300);
//                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" is r2  end.");

        };

        Runnable r = () ->{
            System.out.println(Thread.currentThread().getName()+" is start.");

            FutureTask<String> futureTask = new FutureTask(r2,"ok");
            new Thread(futureTask,"future_task_1").start();


            try {
                String result1 = futureTask.get();
                TimeUnit.SECONDS.sleep(300);
//                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+" is end.");

        };

        FutureTask<String> futureTask = new FutureTask(r,"ok");
//        executor.execute(futureTask);

        new Thread(futureTask,"future_task_0").start();

        long startTime = System.currentTimeMillis();

        String result = futureTask.get();

        long endTime = System.currentTimeMillis();

        System.out.println("result="+result+" diff="+(endTime-startTime));


        TimeUnit.SECONDS.sleep(5);
        executor.shutdown();


    }

}
