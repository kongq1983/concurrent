package com.kq.concurrent.threadlocal;

/**
 * ThreadLocalDemo
 *
 * @author kq
 * @date 2019-05-25
 */
public class MoreThreadLocalDemo {

    public static ThreadLocal<String> value1 = new ThreadLocal<String>();
    public static ThreadLocal<String> value2 = new ThreadLocal<String>();
    public static ThreadLocal<String> value3 = new ThreadLocal<String>();
    public static ThreadLocal<String> value4 = new ThreadLocal<String>();

    public static void main(String[] args) throws Exception {

        System.out.println("---------------start-----------------------");

        new Thread(()->{
            value1.set("milk0");
            value2.set("milk1");
            value3.set("milk2");
            value4.set("milk3");
            // milk
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



    }


}
