package com.kq.concurrent.lock;

/**
 * @author kq
 * @date 2021-03-08 15:16
 * @since 2020-0630
 */
public class BitMoveDemo {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    public static void main(String[] args) {
        // 1001
        System.out.println(exclusiveCount(5));
    }

}
