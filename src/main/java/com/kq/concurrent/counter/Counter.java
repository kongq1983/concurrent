package com.kq.concurrent.counter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Counter {

    volatile int i;

    AtomicLong atomicLong = new AtomicLong(0);

    public void addIndex(){
        i++;
    }

    public synchronized void addSyncIndex(){
        i++;
    }
    public void increment(){
        atomicLong.incrementAndGet();
    }

    public long get(){
        return atomicLong.get();
    }

}
