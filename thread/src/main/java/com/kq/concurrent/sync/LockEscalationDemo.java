package com.kq.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author kq
 * @date 2021-12-30 18:16
 * @since 2020-0630
 */
public class LockEscalationDemo {

    public static void main(String[] args) throws InterruptedException {

//        System.out.println();

        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
        //HotSpot 虚拟机在启动后有个 4s 的延迟才会对每个新建的对象开启偏向锁模式
        Thread.sleep(4000);
        Object obj = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始执行。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName()+"获取锁执行中。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                }
                System.out.println(Thread.currentThread().getName()+"释放锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
            }
        },"thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始执行。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
                synchronized (obj){
                    System.out.println(Thread.currentThread().getName()+"获取锁执行中。。。\n"
                            +ClassLayout.parseInstance(obj).toPrintable());
                }
                System.out.println(Thread.currentThread().getName()+"释放锁。。。\n"
                        +ClassLayout.parseInstance(obj).toPrintable());
            }
        },"thread2").start();

        Thread.sleep(5000);
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

}
