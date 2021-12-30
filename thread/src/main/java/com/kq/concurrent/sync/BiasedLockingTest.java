package com.kq.concurrent.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 以kclass为单位，为每个class维护一个偏向锁撤销计数器，每一次该class的对象发生偏向撤销操作时，该计数器+1，当这个值达到重偏向阈值（默认20）时，JVM就认为该class的偏向锁有问题，因此会进行批量重偏向
 * =批量重偏向(-XX:BiasedLockingBulkRebiasThreshold)
 * @author kq
 * @date 2021-12-30 18:29
 * @since 2020-0630
 */
public class BiasedLockingTest {

    public static void main(String[] args) throws Exception{
        //延时产生可偏向对象
        Thread.sleep(5000);
        // 创建一个list，来存放锁对象
        List<Object> list = new ArrayList<>();

        // 线程1
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                // 新建锁对象
                Object lock = new Object();
                synchronized (lock) {
                    list.add(lock);
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
                        System.out.println("thread2-第" + (i + 1) + "次加锁执行中\t"+ //obj +","+
                                ClassLayout.parseInstance(obj).toPrintable());
                    }
                }
                if(i==17||i==19){
                    System.out.println("thread2-第" + (i + 1) + "次释放锁\t"+ // obj +","+
                            ClassLayout.parseInstance(obj).toPrintable());
                }
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thead2").start();

        LockSupport.park();
    }

}
