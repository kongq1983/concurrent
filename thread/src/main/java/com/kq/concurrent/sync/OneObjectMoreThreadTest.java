package com.kq.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 批量重偏向和批量撤销是针对类的优化，和对象无关
 * 偏向锁重偏向一次之后不可再次重偏向。
 * 当某个类已经触发批量撤销机制后，JVM会默认当前类产生了严重的问题，剥夺了该类的新实例对象使用偏向锁的权利
 * 当撤销偏向锁阈值超过 40 次后，jvm 会认为不该偏向，于是整个类的所有对象都会变为不可偏向的，新建的对象也是不可偏向的，直接进入轻量级锁
 * -XX:BiasedLockingDecayTime=25000ms范围内没有达到40次，撤销次数清为0，重新计时
 * @author kq
 * @date 2021-12-30 19:08
 * @since 2020-0630
 */
public class OneObjectMoreThreadTest {

    public static void main(String[] args) throws  InterruptedException {
        //延时产生可偏向对象
        Thread.sleep(5000);
        // 创建一个list，来存放锁对象
        List<Object> list = new ArrayList<>();
        Object lock1 = new Object();

        // 线程1
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                // 新建锁对象
//                Object lock1 = new Object();
                synchronized (lock1) {
                    list.add(lock1);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead1").start();

        //睡眠3s钟保证线程thead1创建对象完成
        Thread.sleep(3000);
        System.out.println("打印thead1，list中第20个对象的对象头：");
        System.out.println((ClassLayout.parseInstance(list.get(19)).toPrintable()));

        // 线程2
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                Object obj = list.get(i);
                synchronized (obj) {
                    if(i>=15&&i<=21||i>=38){
                        System.out.println("thread2-第" + (i + 1) + "次加锁执行中\t"+
                                ClassLayout.parseInstance(obj).toPrintable());
                    }
                }
                if(i==17||i==19){
                    System.out.println("thread2-第" + (i + 1) + "次释放锁\t"+
                            ClassLayout.parseInstance(obj).toPrintable());
                }
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead2").start();


        // 这里注释掉，就会重量级锁
//        Thread.sleep(3000);

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                Object lock =list.get(i);
                if(i>=17&&i<=21||i>=35&&i<=41){
                    System.out.println("thread3-第" + (i + 1) + "次准备加锁\t"+
                            ClassLayout.parseInstance(lock).toPrintable());
                }
                synchronized (lock){
                    if(i>=17&&i<=21||i>=35&&i<=41){
                        System.out.println("thread3-第" + (i + 1) + "次加锁执行中\t"+
                                ClassLayout.parseInstance(lock).toPrintable());
                    }
                }
            }
        },"thread3").start();

        Thread.sleep(3000);
        System.out.println("查看新创建的对象");
        System.out.println((ClassLayout.parseInstance(new Object()).toPrintable()));

        LockSupport.park();
    }

}
