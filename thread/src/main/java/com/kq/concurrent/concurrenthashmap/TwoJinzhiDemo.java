package com.kq.concurrent.concurrenthashmap;

/**
 * TwoJinzhiDemo
 *
 * @author kq
 * @date 2022-01-04 22:59
 * @since 1.0.0
 */
public class TwoJinzhiDemo {

    public static void main(String[] args) {
        int n = 18;

        System.out.println(Integer.toBinaryString(n));

        int n1 = Integer.numberOfLeadingZeros(n);
        System.out.println(n1);
        // 100000,00000,00000
        System.out.println(Integer.toBinaryString(1 << (16 - 1)));
    }

    // Integer.numberOfLeadingZeros(n): 二进制标识的从高位开始到第一个非0的数字的之间0的个数
    static final int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }

}
