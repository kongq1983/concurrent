package com.kq.concurrent.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 验证allowCoreThreadTimeOut=true 最后会保留1个workder
 * AllowCoreThreadTimeOutTrueDemo
 * 最终poolSize=0
 * @author kq
 * @date 2021/9/6 23:37
 * @since 1.0.0
 */
public class AllowCoreThreadTimeOutTrueDemo {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,5,
            5, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));

    public static void main(String[] args) throws Exception{

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i=0;i<10;i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--------------------");
            });
        }

        Runnable runnable = ()->{
            while (true) {
                int poolSize = threadPoolExecutor.getPoolSize();
                System.out.println("poolSize="+poolSize+","+threadPoolExecutor.allowsCoreThreadTimeOut());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread r = new Thread(runnable);
        r.start();

        TimeUnit.MINUTES.sleep(2);


    }

}
