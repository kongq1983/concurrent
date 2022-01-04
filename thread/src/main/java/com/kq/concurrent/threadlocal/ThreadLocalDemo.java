package com.kq.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocalDemo
 *
 * @author kq
 * @date 2019-05-25
 */
public class ThreadLocalDemo {

    public static ThreadLocal<String> value = new ThreadLocal<String>();

    public static void main(String[] args) throws Exception {

        System.out.println("---------------start-----------------------");

        new Thread(()->{
            value.set("milk0");
            value.set("milk1");
            value.set("milk2");
            value.set("milk3");

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // milk
            System.out.println("get="+value.get());

            value.remove();  // 删除ThreadLocal
            System.out.println();

        },"thread-1").start();


//        new Thread(()->{
//            try {
//                Thread.sleep(1000L);
//                value.set("milk11");
//                // null
//                System.out.println("get1="+value.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"thread-2").start();

        TimeUnit.MINUTES.sleep(10);

    }


}
