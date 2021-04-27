package com.kq;

/**
 * A
 *
 * @author kq
 * @date 2019-06-03
 */
public class A {

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    public static void main(String[] args) {

        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println(tmpDir);

//        WSDC_KITCHEN_RECEIPT_QRCODE_VIEW
        //0000 0111
        //0011 1000
        int num = (7 << 3);
        System.out.println(num);

        int five = (1 << 5); //32
        System.out.println("five=" + five);

        int eigh = (1 << 8); //256
        System.out.println("eigh=" + eigh);

        int eigh1 = (1 << 16); //65536
        System.out.println("eigh1=" + eigh1);

        long eigh2 = (1l << 32); //4294967296
        System.out.println("eigh2=" + eigh2);

        System.out.println("SHARED_SHIFT="+SHARED_SHIFT);
        System.out.println("SHARED_UNIT="+SHARED_UNIT);
        System.out.println("MAX_COUNT="+MAX_COUNT);
        System.out.println("EXCLUSIVE_MASK="+EXCLUSIVE_MASK);
    }

}
