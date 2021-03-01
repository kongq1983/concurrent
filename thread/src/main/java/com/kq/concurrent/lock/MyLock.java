package com.kq.concurrent.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * MyLock
 *
 * @author kq
 * @date 2019-05-27
 */
public class MyLock implements Lock{

    private AtomicReference<Thread> owner = new AtomicReference<>();
    private BlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    @Override
    public void lock() {
        while(!tryLock()){
            Thread curTh = Thread.currentThread();
            waiters.offer(curTh);
            LockSupport.park();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        if(owner==null)throw new NullPointerException("空异常! owner=null"+owner);

        boolean result = owner.compareAndSet(null,Thread.currentThread());
        System.out.printf(Thread.currentThread().getName()+" 尝试获取锁  result=%s \n",result);

        return result;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        if (owner.compareAndSet(Thread.currentThread(), null)){
//            owner = null;

            System.out.printf(Thread.currentThread().getName()+"释放锁! \n");

            LockSupport.unpark(waiters.poll());
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
