package com.kq.concurrent.guava.feture.demo1;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2020-12-15 14:52
 * @since 2020-0630
 */
public class RateLimiterDemo {

    private static RateLimiter limiter = RateLimiter.create(10);

    private static void yw(){
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println(Thread.currentThread().getName()+",is leave!");
        }catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public static void main(String[] args) throws Exception{

//        Runnable runnable = ()->{
//             if(limiter.tryAcquire()) {
//                 System.out.println(LocalDateTime.now()+",获取锁!"+Thread.currentThread().getName());
//                 yw();
//             }else {
//                 System.out.println(LocalDateTime.now()+",没有获取锁!"+Thread.currentThread().getName());
//             }
//        };

        Thread[] ts = new Thread[20];
        int index = 1;
        for(Thread t : ts) {
            t = new Thread(()->{
//                if(limiter.tryAcquire(1,30,TimeUnit.MILLISECONDS)) {
                if(limiter.tryAcquire(1,1,TimeUnit.SECONDS)) {
                    System.out.println(LocalDateTime.now()+",获取锁!"+Thread.currentThread().getName());
                    yw();
                }else {
                    System.out.println(LocalDateTime.now()+",没有获取锁!"+Thread.currentThread().getName());
                }
            },"thread-"+String.valueOf(index));
//            TimeUnit.MILLISECONDS.sleep(50);
            t.start();
            index++;
        }


        TimeUnit.SECONDS.sleep(60);
    }

}
