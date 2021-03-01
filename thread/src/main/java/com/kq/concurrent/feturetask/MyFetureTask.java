package com.kq.concurrent.feturetask;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * FetureTaskDemo
 *
 * @author kq
 * @date 2019/5/23
 */
public class MyFetureTask<T> implements Runnable{

    private volatile boolean  isEnding = false;

    private Callable<T> callable;

    private T result;

    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue(100);

    public MyFetureTask(Callable<T> callable) {
        this.callable = callable;
    }

    @Override
    public void run(){
        try{
            result = callable.call();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            isEnding = true;
        }

        while(true) {
            Thread writer = waiters.poll();
            if(writer==null){
                break;
            }

            LockSupport.unpark(writer);
        }
    }

    public T get(){

        if(!isEnding) {
            waiters.offer(Thread.currentThread());
        }

        while(!isEnding) {
            LockSupport.park();
        }

        return result;

    }

}
