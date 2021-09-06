package com.kq.concurrent.executor;

/**
 * ThreadPoolExecutorDemo
 *
 * @author kq
 * @date 2021/9/6 21:21
 * @since 1.0.0
 */
public class ThreadPoolExecutorDemo {
    private static final int COUNT_BITS = Integer.SIZE - 3; // 29
    private static final int RUNNING    = -1 << COUNT_BITS; // -536870912
    private static final int SHUTDOWN   =  0 << COUNT_BITS; // 0
    private static final int STOP       =  1 << COUNT_BITS; // 536870912
    private static final int TIDYING    =  2 << COUNT_BITS; // 1073741824
    private static final int TERMINATED =  3 << COUNT_BITS; // 1610612736

    public static void main(String[] args) {
        System.out.println(COUNT_BITS);
        System.out.println(RUNNING);
        System.out.println(SHUTDOWN);
        System.out.println(STOP);
        System.out.println(TIDYING);
        System.out.println(TERMINATED);
    }

}
