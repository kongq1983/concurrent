package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * 偏向锁 -> 锁测消 -> 轻量级锁
 * @author kq
 * @date 2022-01-25 9:42
 * @since 2020-0630
 */
public class BiasedLockToLightWeightByHashCodeCallMore {


    public static void main(String[] args) throws Exception{

        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms

        final NoLockToLightBiasedLock.A a = new NoLockToLightBiasedLock.A();
        ClassLayout layout = ClassLayout.parseInstance(a);



        out.println("**** Before object");
        out.println(layout.toPrintable());  // 偏向锁

        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁

        a.hashCode();  // 会撤销偏向锁，变无锁

        out.println("**** after Call hashCode");
        out.println(layout.toPrintable()); // 无锁

        synchronized (a) {
            a.hashCode();  // 由于上面已经生成hashcode了，所以这个hashcode可以忽略(如果这里是第一次生成hashcode，则下面直接重量级锁)
            a.hashCode(); // 多次调用可以忽略
            a.hashCode();
            out.println("**** With the lock");
            out.println(layout.toPrintable()); // synchronized之前调用hashcode ，则这里是轻量级锁
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable()); // 无锁
    }


}
