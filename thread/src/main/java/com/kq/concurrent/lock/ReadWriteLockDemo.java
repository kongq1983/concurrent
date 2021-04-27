package com.kq.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kq
 * @date 2021-03-08 15:23
 * @since 2020-0630
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) throws Exception{
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // 先拿读锁  同个线程拿去拿写锁会失败
        // 先拿写锁  同个线程去拿读锁 会成功
        try {

            readWriteLock.writeLock().lock();

            readWriteLock.readLock().lock();
            readWriteLock.readLock().lock();
            readWriteLock.readLock().lock();

//            readWriteLock.writeLock().lock();

            TimeUnit.MINUTES.sleep(10);

        }finally {
            readWriteLock.readLock().unlock();
            readWriteLock.readLock().unlock();
            readWriteLock.readLock().unlock();
        }

    }

}
