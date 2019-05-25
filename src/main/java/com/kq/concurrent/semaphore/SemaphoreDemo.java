package com.kq.concurrent.semaphore;

import com.kq.concurrent.util.DateUtil;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * SemaphoreDemo
 *
 * @author kq
 * @date 2019-05-25
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        for (int i=0; i<1000; i++){

            new Thread(()->{

                try {
                    System.out.println(DateUtil.getNowTime()+" "+Thread.currentThread().getName()+" 开始排队查询数据库!");
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queryDb("localhost:3306");  //模拟DB查询
                semaphore.release();

            }).start();

        }

    }

    //发送一个HTTP请求
    public static void queryDb(String uri)  {
        System.out.println(DateUtil.getNowTime()+" "+Thread.currentThread().getName()+" 开始查询数据库:" + uri);
        LockSupport.parkNanos(1000 * 1000 * 1000);
    }

}
