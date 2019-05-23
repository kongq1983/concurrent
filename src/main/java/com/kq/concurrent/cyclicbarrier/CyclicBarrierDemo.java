package com.kq.concurrent.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

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
                System.out.println(" >>> 这是一个栅栏.");
                try {
                    Thread.sleep(1500);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        for(int i=0;i<100;i++) {
            new Thread(()->{
                try {

                    barrier.await();
                    System.out.println(Thread.currentThread().getName()+"号运动员，到达营地!");
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }


    }
}
