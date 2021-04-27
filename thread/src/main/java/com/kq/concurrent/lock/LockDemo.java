package com.kq.concurrent.lock;

import com.kq.concurrent.util.PropertiesUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kq
 * @date 2021-03-01 10:57
 * @since 2020-0630
 */
public class LockDemo {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);

        lock.lock();

        Runnable runnable = ()->{

            lock.lock();
            try {
                System.out.println("------------------------------------------------");
            }finally {
                lock.unlock();
            }

        };

        new Thread(runnable).start();

        try{
            System.out.println("=======================================================");

//            TimeUnit.MINUTES.sleep(10);
            while (PropertiesUtil.isLock()) {
                TimeUnit.SECONDS.sleep(5);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }



    }

}
