package com.kq.concurrent.jol;

/**
 * https://hg.openjdk.java.net/code-tools/jol/file/56dbba3b2c20/jol-samples/src/main/java/org/openjdk/jol/samples/JOLSample_02_Alignment.java
 */
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author Aleksey Shipilev
 */
public class JOLSample_02_Alignment {

    /*
     * This is the more advanced field layout example.
     *
     * Because the underlying hardware platform often requires aligned accesses
     * to maintain the performance and correctness, it is expected the fields
     * are aligned by their size. For booleans it does not mean anything, but
     * for longs it's different. In this example, we can see the long field
     * is indeed aligned for 8 bytes, sometimes making the gap after the
     * object header.
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());

    }

    public static class A {
        long f;
    }

}