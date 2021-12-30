package com.kq.concurrent.readwrite;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kq
 * @date 2021-09-09 16:13
 * @since 2020-0630
 */
public class ReentrantLockDemo {

    static ReentrantLock lock = new ReentrantLock(true);


    public static void main(String[] args) throws Exception{

        Condition condition = lock.newCondition();

        Runnable r = ()-> {

            try {
                lock.lock();
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };

        Thread t = new Thread(r);
        t.start();

        TimeUnit.SECONDS.sleep(3);


        condition.await(); // 这个一定要在lock内  否则java.lang.IllegalMonitorStateException

        TimeUnit.HOURS.sleep(1);

    }

}
