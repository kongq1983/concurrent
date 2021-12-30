package com.kq.concurrent.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * https://hg.openjdk.java.net/code-tools/jol/file/56dbba3b2c20/jol-samples/src/main/java/org/openjdk/jol/samples/JOLSample_13_BiasedLocking.java
 * @author Aleksey Shipilev
 */
public class JOLSample_13_BiasedLocking {

    /*
     * This is the example of biased locking.
     *
     * In order to demonstrate this, we first need to sleep for >5 seconds
     * to pass the grace period of biased locking. Then, we do the same
     * trick as the example before. You may notice that the mark word
     * had not changed after the lock was released. That is because
     * the mark word now contains the reference to the thread this object
     * was biased to.
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());



        final A a = new A();
        ClassLayout layout = ClassLayout.parseInstance(a);

        out.println("**** Before object");
        out.println(layout.toPrintable());  // 这里为什么是偏向锁
        TimeUnit.SECONDS.sleep(6);



        out.println("**** Fresh object");
        out.println(layout.toPrintable());

        synchronized (a) {
            out.println("**** With the lock");
            out.println(layout.toPrintable());
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }

}
