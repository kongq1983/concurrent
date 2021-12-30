package com.kq.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kq
 * @date 2021-11-30 11:05
 * @since 2020-0630
 */
public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) throws Exception{

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        writeLock.lock();

        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();
        readLock.lock();

        readLock.unlock();
        readLock.unlock();
        readLock.unlock();
        readLock.unlock();
        readLock.unlock();

        TimeUnit.MINUTES.sleep(60);


    }

}
