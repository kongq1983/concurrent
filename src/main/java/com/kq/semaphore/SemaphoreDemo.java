package com.kq.semaphore;

import java.util.concurrent.Semaphore;

/**
 * SemaphoreDemo
 *
 * @author kq
 * @date 2019/5/23
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);

        for(int i=0;i<1000;i++) {
            new Thread(()->{

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+" execute.");
                    Thread.sleep(1500l);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                semaphore.release();

            }).start();
        }


    }

}
