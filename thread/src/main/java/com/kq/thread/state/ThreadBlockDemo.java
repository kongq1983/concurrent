package com.kq.thread.state;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2022-11-02 16:50
 * @since 2020-0630
 */
public class ThreadBlockDemo {

    public static void main(String[] args) throws Exception{

        Object lock = new Object();

        Runnable r = ()->{

            synchronized (lock) {
                System.out.println(Thread.currentThread()+",获得锁!");
                try {
                    TimeUnit.SECONDS.sleep(3);
//                    lock.wait();

//                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };

        Thread t1 = new Thread(r,"thread-t1");
        t1.setDaemon(true);

        Thread t2 = new Thread(r,"thread-t2");
        t2.setDaemon(true);

        for(int i=0;i<8;i++) {

            if(i==2){
                t1.start();
                t2.start();
            }

            System.out.println(t1.getName()+", state="+t1.getState());
            System.out.println(t2.getName()+", state="+t2.getState());
            TimeUnit.SECONDS.sleep(1);

        }

    }

}
