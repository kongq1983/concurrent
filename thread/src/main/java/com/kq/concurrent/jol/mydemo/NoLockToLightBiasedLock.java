package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @author kq
 * @date 2022-01-24 11:01
 * @since 2020-0630
 */
public class NoLockToLightBiasedLock {

    public static void main(String[] args) throws Exception{

        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms

        final NoLockToLightBiasedLock.A a = new NoLockToLightBiasedLock.A();
        ClassLayout layout = ClassLayout.parseInstance(a);



        out.println("**** Before object");
        out.println(layout.toPrintable());  // 偏向锁




        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁

        synchronized (a) {
            out.println("**** With the lock");
            out.println(layout.toPrintable()); // 偏向锁
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable()); //偏向锁
    }

    public static class A {
        // no fields
    }

}
