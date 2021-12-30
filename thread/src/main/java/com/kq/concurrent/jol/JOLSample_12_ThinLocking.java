package com.kq.concurrent.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author Aleksey Shipilev
 */
public class JOLSample_12_ThinLocking {

    /*
     * This is another dive into the mark word.
     *
     * Among other things, mark words store locking information.
     * We can clearly see how the mark word contents change when
     * we acquire the lock, and release it subsequently.
     *
     * This one is the example of thin (displaced) lock. The data
     * in mark word when lock is acquired is the reference to the
     * displaced object header, allocated on stack. Once we leave
     * the lock, the displaced header is discarded, and mark word
     * is reverted to the default value.
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());

        final A a = new A();



        ClassLayout layout = ClassLayout.parseInstance(a);

        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁
        out.println("a hashCode: " + Integer.toHexString(a.hashCode()));
        out.println("before lock , main thread. hashCode: " + Integer.toHexString(Thread.currentThread().hashCode()));
        out.println("before lock , main thread. id: " + Thread.currentThread().getId());
        out.println("before lock , main thread. bi hashCode: " + Integer.toBinaryString(Thread.currentThread().hashCode()));

        synchronized (a) {
            out.println("**** With the lock");
            out.println("a hashCode: " + Integer.toBinaryString(a.hashCode()));
            out.println("locked main thread. hashCode: " + Integer.toHexString(Thread.currentThread().hashCode()));
            out.println("locked main thread. id: " + Thread.currentThread().getId());
            out.println("locked main thread. bi hashCode: " + Integer.toBinaryString(Thread.currentThread().hashCode()));
            out.println(layout.toPrintable()); // 轻量级锁
        }

        out.println("**** After the lock");
        out.println("a hashCode: " + Integer.toHexString(a.hashCode()));
        out.println("after lock , main thread. hashCode: " + Integer.toHexString(Thread.currentThread().hashCode()));
        out.println("after lock , main thread. id: " + Thread.currentThread().getId());
        out.println("after lock , main thread. bi hashCode: " + Integer.toBinaryString(Thread.currentThread().hashCode()));

        out.println(layout.toPrintable()); // 无锁
    }

    public static class A {
        // no fields
    }

}
