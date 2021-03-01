package com.kq.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 自己实现的线程安全类
 * @since 2021-01-30
 * @author kq
 */
public class CounterUnsafe {

    volatile int i=0;

    private static Unsafe unsafe = null;
    // 代表要修改的字段
    private static long valueOffset;

    private AtomicLong retryTimes = new AtomicLong();

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            //指定要修改的字段
            Field iField = CounterUnsafe.class.getDeclaredField("i");
            //偏移量
            valueOffset = unsafe.objectFieldOffset(iField);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void add(){
        // 对象、修改的值offset、当前值、目标值
        while (!unsafe.compareAndSwapInt(this,valueOffset,i,i+1)){
            retryTimes.incrementAndGet();
        }
    }

    public int get(){
        return i;
    }

    public long getRetryTimes(){
        return retryTimes.get();
    }

    public static void main(String[] args) throws Exception{

        int threadsize = 6;
        CountDownLatch countDownLatch = new CountDownLatch(threadsize);

        CounterUnsafe counterUnsafe = new CounterUnsafe();
        Runnable runnable = ()->{
            for(int i=0;i<10000;i++) {
                counterUnsafe.add();
            }
            countDownLatch.countDown();
        };

        for(int i=0;i<threadsize;i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.println("i="+counterUnsafe.get()+",retryTimes="+counterUnsafe.getRetryTimes());
    }

}
