package com.kq;

/**
 * A
 *
 * @author kq
 * @date 2019-06-03
 */
public class A
{

    public static void main(String[] args) {

        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println(tmpDir);

//        WSDC_KITCHEN_RECEIPT_QRCODE_VIEW
        //0000 0111
        //0011 1000
        int num = (7 << 3);
        System.out.println(num);

        int five = (1<<5); //32
        System.out.println("five="+five);

        int eigh = (1<<8); //256
        System.out.println("eigh="+eigh);

        int eigh1 = (1<<16); //65536
        System.out.println("eigh1="+eigh1);

        long eigh2 = (1l << 32); //4294967296
        System.out.println("eigh2="+eigh2);
    }

}
