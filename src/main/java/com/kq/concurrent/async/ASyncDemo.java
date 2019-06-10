package com.kq.concurrent.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ASyncDemo
 *
 * @author kq
 * @date 2019-06-10
 */
public class ASyncDemo {

    private static ExecutorService executor = Executors.newSingleThreadExecutor();


    public static void main(String[] args) {
        execute("1");
        execute("2");
        execute("3");
        System.out.println("-----------------------------------");

    }


    public static void execute(String orderId) {
        System.out.println("call execute "+orderId);
        executor.submit(()->{
            System.out.println("start execute orderId "+orderId);
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end execute orderId" +orderId);
        });
    }

}
