package com.kq.concurrent.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己实现锁
 */
public class KqLock implements Lock {

    private Thread owner = null;

    private BlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    @Override
    public void lock() {
//        if(!tryLock()) {
//            LockSupport.park();
//            // 递归 不好
//            lock();
//        }

        while (!tryLock()) {
            waiters.offer(Thread.currentThread());
            LockSupport.park();
        }

        System.out.println("拿到锁="+Thread.currentThread().getName());

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        if(owner==null) {
            owner = Thread.currentThread();
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if(owner==Thread.currentThread()) {
            System.out.println("释放锁="+owner.getName());
            owner = null;

            Thread notifyThread = waiters.poll();
            System.out.println("唤醒线程="+notifyThread);
            LockSupport.unpark(notifyThread);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }


    public static void main(String[] args) {
        Thread[] ts = new Thread[5];

        KqLock lock = new KqLock();
        int index = 1;
        for(Thread t : ts) {
            t = new Thread(()->{

                try {
                    lock.lock();
                    Thread.sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },"thread-"+index);
            ts[index-1] = t;
            index++;
        }

        for(Thread t : ts) {
            t.start();
        }

    }

}
