package com.kq.concurrent.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLockDemo
 *
 * @author kq
 * @date 2019/5/30
 */
public class ReadWriteLockDemo {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final Map<String,Object> map = new HashMap<>();

    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();




    public static void main(String[] args) {

    }

}
