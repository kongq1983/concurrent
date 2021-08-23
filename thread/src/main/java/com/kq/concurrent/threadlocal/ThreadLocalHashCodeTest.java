package com.kq.concurrent.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocalHashCodeTest
 *
 * @author kq
 * @date 2021/8/23 22:14
 * @since 1.0.0
 */
public class ThreadLocalHashCodeTest {

    private final int threadLocalHashCode = nextHashCode();



    private static AtomicInteger nextHashCode =
            new AtomicInteger();



    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }



    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) {

        ThreadLocalHashCodeTest test = new ThreadLocalHashCodeTest();


        System.out.println(test.threadLocalHashCode);
        System.out.println(test.threadLocalHashCode);

        System.out.println("====================================");
//        System.out.println(ThreadLocalHashCodeTest.threadLocalHashCode);
//        System.out.println(ThreadLocalHashCodeTest.threadLocalHashCode);
//        System.out.println(ThreadLocalHashCodeTest.threadLocalHashCode);
//        System.out.println(ThreadLocalHashCodeTest.threadLocalHashCode);
        System.out.println(ThreadLocalHashCodeTest.nextHashCode());
        System.out.println(ThreadLocalHashCodeTest.nextHashCode());
        System.out.println(ThreadLocalHashCodeTest.nextHashCode());
        System.out.println(ThreadLocalHashCodeTest.nextHashCode());
        System.out.println(ThreadLocalHashCodeTest.nextHashCode());

    }

}
