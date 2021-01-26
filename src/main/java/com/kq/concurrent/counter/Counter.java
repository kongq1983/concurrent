package com.kq.concurrent.counter;

public class Counter {

    volatile int i;

    public void addIndex(){
        i++;
    }

    public synchronized void addSyncIndex(){
        i++;
    }

}
