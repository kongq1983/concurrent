package com.kq.concurrent.jol.mydemo;

import com.kq.concurrent.jol.JOLSample_13_BiasedLocking;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @author kq
 * @date 2022-01-24 11:01
 * @since 2020-0630
 */
public class NoLockToLightWeightLock {

    public static void main(String[] args) throws Exception{

        final NoLockToLightWeightLock.A a = new NoLockToLightWeightLock.A();
        ClassLayout layout = ClassLayout.parseInstance(a);

        out.println("**** Before object");
        out.println(layout.toPrintable());  // 无锁
        TimeUnit.SECONDS.sleep(6);  // 这里加不加都一样，因为创建对象在4s之前，所以是无锁



        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 无锁

        synchronized (a) {
            out.println("**** With the lock");
            out.println(layout.toPrintable()); // 轻量级锁
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable()); //无锁
    }

    public static class A {
        // no fields
    }

}
