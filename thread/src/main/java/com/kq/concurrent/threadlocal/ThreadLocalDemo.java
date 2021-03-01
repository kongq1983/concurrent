package com.kq.concurrent.threadlocal;

/**
 * ThreadLocalDemo
 *
 * @author kq
 * @date 2019-05-25
 */
public class ThreadLocalDemo {

    public static ThreadLocal<String> value = new ThreadLocal<String>();

    public static void main(String[] args) throws Exception {

        new Thread(()->{
            value.set("milk");

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // milk
            System.out.println("get="+value.get());
        }).start();


        new Thread(()->{
            try {
                Thread.sleep(1000L);
                // null
                System.out.println("get1="+value.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



    }


}
