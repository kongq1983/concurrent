package com.kq.concurrent.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kq
 * @date 2021-09-09 16:13
 * @since 2020-0630
 */
public class ReentrantLockDemo1 {

    static ReentrantLock lock = new ReentrantLock(true);


    public static void main(String[] args) throws Exception{

        Condition condition = lock.newCondition();



        Runnable r1 = ()-> {

            try {
                lock.lock();
                System.out.println(Thread.currentThread()+", is 获得锁");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread()+", 开始调用await()");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };

        Thread t = new Thread(r1,"thread-1");
        t.start();


        Runnable r2 = ()-> {

            try {
                lock.lock();
                System.out.println(Thread.currentThread()+", is 获得锁");
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };

        Thread t2 = new Thread(r2,"thread-2");
        t2.start();

        TimeUnit.SECONDS.sleep(3);

        TimeUnit.HOURS.sleep(1);

    }

}
