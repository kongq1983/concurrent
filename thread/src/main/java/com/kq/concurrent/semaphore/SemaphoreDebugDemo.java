package com.kq.concurrent.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-06-29 8:51
 * @since 2020-0630
 */
public class SemaphoreDebugDemo {



    public static void main0(String[] args) throws Exception{
        Semaphore semaphore = new Semaphore(3);

        Runnable r = ()->{

            try {
                semaphore.acquire();

                TimeUnit.MINUTES.sleep(10);

            }catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                semaphore.release();
            }

        };


        Thread[] ts = new Thread[5];

        for(int i=0;i<ts.length;i++) {
            ts[i] = new Thread(r,"thread-"+i);
            ts[i].start();
        }


        TimeUnit.SECONDS.sleep(3);

        System.out.println("=======================================");

        try {
            semaphore.acquire();
        }finally {
            semaphore.release();
        }

        TimeUnit.SECONDS.sleep(60);

    }

    /**
     * 测试acquire
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Semaphore semaphore = new Semaphore(3);

        try {
            semaphore.acquire();
        }finally {
            semaphore.release();
        }

    }

}
