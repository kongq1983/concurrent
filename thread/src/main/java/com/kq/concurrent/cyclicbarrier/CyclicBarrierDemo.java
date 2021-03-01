package com.kq.concurrent.cyclicbarrier;

import com.kq.A;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * CyclicBarrierDemo
 * 每4个一批
 * @author kq
 * @date 2019/5/23
 */
public class CyclicBarrierDemo {

//    Thread-0号运动员，到达1号营地!
//    Thread-1号运动员，到达1号营地!
//    Thread-2号运动员，到达1号营地!
//    Thread-3号运动员，到达1号营地!
//            >>> 出发，去2号营地.
//            Thread-4号运动员，到达1号营地!
//    Thread-5号运动员，到达1号营地!
//    Thread-6号运动员，到达1号营地!
//    Thread-7号运动员，到达1号营地!
//            >>> 出发，去3号营地.
//            Thread-8号运动员，到达1号营地!
//    Thread-9号运动员，到达1号营地!
//    Thread-10号运动员，到达1号营地!
//    Thread-11号运动员，到达1号营地!
//            >>> 出发，去4号营地.

    public static void main(String[] args) throws Exception {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {

                try {
//                    Thread.sleep(1500);
                    System.out.println(" >>> 出发，去"+atomicInteger.getAndIncrement()+"号营地.");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        for(int i=0;i<100;i++) {
            new Thread(()->{
                try {

                    System.out.println(Thread.currentThread().getName()+"号运动员，到达1号营地!");
                    barrier.await();
//                    System.out.println(Thread.currentThread().getName()+"号运动员，准备出发，去下一营地!");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }

        LockSupport.parkNanos(1000 * 1000 * 1000L);

    }
}
