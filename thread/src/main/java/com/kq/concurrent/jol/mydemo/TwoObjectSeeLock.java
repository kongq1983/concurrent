package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @author kq
 * @date 2022-01-26 16:10
 * @since 2020-0630
 */
public class TwoObjectSeeLock {

    public static void main(String[] args) throws Exception{

        Object obj1 = new Object();

        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms

        Object obj2 = new Object();

        ClassLayout layout1 = ClassLayout.parseInstance(obj1);

        out.println("**** obj1 object");
        out.println(layout1.toPrintable());  // 无锁

        ClassLayout layout2 = ClassLayout.parseInstance(obj2);

        out.println("**** obj2 object");
        out.println(layout2.toPrintable());  // 偏向锁
    }

}
