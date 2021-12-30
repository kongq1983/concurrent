package com.kq.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kq
 * @date 2021-06-29 8:23
 * @since 2020-0630
 */
public class ReentrantLockConditionDemo {

    public static void main(String[] args) throws Exception{
        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        try {
            condition.await();
        }catch (Exception e) {
            e.printStackTrace();
        }

        TimeUnit.MINUTES.sleep(1);
    }

}
