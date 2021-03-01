package com.kq.concurrent.jol;


import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * https://hg.openjdk.java.net/code-tools/jol/file/56dbba3b2c20/jol-samples/src/main/java/org/openjdk/jol/samples/JOLSample_01_Basic.java
 */
public class JOLSample_01_Basic {

    /*
     * This sample showcases the basic field layout.
     * You can see a few notable things here:
     *   a) how much the object header consumes;
     *   b) how fields are laid out;
     *   c) how the external alignment beefs up the object size
     */

    public static void main(String[] args) throws Exception {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());
    }

    public static class A {
        boolean f;
    }


}
