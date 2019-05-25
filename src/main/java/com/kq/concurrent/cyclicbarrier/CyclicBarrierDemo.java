package com.kq.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.LockSupport;

/**
 * CyclicBarrierDemo
 *
 * @author kq
 * @date 2019/5/23
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1500);
                    System.out.println(" >>> 出发，去2号营地.");
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

                }catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }

        LockSupport.parkNanos(1000 * 1000 * 1000L);

    }
}
