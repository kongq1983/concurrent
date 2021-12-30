package com.kq.concurrent.jol.mydemo;

import com.kq.concurrent.jol.JOLSample_12_ThinLocking;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author kq
 * @date 2021-12-29 10:20
 * @since 2020-0630
 */
public class HashCodeDemo2 {

    public static void main(String[] args) {
        out.println(VM.current().details());

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);

        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁
        out.println("a hashCode: " + Integer.toHexString(a.hashCode())); // hashcode一定要调用 否则不会触发
//        out.println("before lock , main thread. hashCode: " + Integer.toHexString(Thread.currentThread().hashCode()));
//        out.println("before lock , main thread. id: " + Thread.currentThread().getId());
//        out.println("before lock , main thread. bi hashCode: " + Integer.toBinaryString(Thread.currentThread().hashCode()));

        synchronized (a) {
            out.println("**** With the lock");
            out.println("a hashCode: " + Integer.toBinaryString(a.hashCode())); // hashcode一定要调用 否则不会触发
//            out.println("locked main thread. hashCode: " + Integer.toHexString(Thread.currentThread().hashCode()));
//            out.println("locked main thread. id: " + Thread.currentThread().getId());
//            out.println("locked main thread. bi hashCode: " + Integer.toBinaryString(Thread.currentThread().hashCode()));
            out.println(layout.toPrintable()); // 轻量级锁
        }

        out.println("**** After the lock");
        out.println("a hashCode: " + Integer.toHexString(a.hashCode())); // hashcode一定要调用 否则不会触发
//        out.println("after lock , main thread. hashCode: " + Integer.toHexString(Thread.currentThread().hashCode()));
//        out.println("after lock , main thread. id: " + Thread.currentThread().getId());
//        out.println("after lock , main thread. bi hashCode: " + Integer.toBinaryString(Thread.currentThread().hashCode()));

        out.println(layout.toPrintable()); // 无锁
    }


    public static class A {
        // no fields
    }

}
