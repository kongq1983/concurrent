package com.kq.concurrent.interrupt;

/**
 * ThreadInputDemo1
 *
 * @author kq
 * @date 2019/5/23
 */
public class ThreadInputDemo1 {

    public static void main(String[] args) throws Exception{

        Thread t = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()){

                System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().isInterrupted());

            }

            System.out.println("The End ! isInterrupted="+Thread.currentThread().isInterrupted());

        });

        t.start();

        Thread.sleep(2000);

        t.interrupt();

    }

}
