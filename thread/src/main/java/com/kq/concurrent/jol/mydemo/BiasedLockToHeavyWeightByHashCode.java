package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * 偏向锁 -> 锁测消 -> 重量级锁
 * @author kq
 * @date 2022-01-25 9:42
 * @since 2020-0630
 */
public class BiasedLockToHeavyWeightByHashCode {


    public static void main(String[] args) throws Exception{

        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms  注释这个 synchronized之前，偏向锁变无锁

        final NoLockToLightBiasedLock.A a = new NoLockToLightBiasedLock.A();
        ClassLayout layout = ClassLayout.parseInstance(a);



        out.println("**** Before object");
        out.println(layout.toPrintable());  // 偏向锁




        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁


        synchronized (a) {
            a.hashCode(); // 在synchronized里(第一次生成hashcode)，则下面直接重量级锁
            out.println("**** With the lock");
            out.println(layout.toPrintable());
        }  // 感觉只是释放_owner，没处理markword

        out.println("**** After the lock");
        out.println(layout.toPrintable()); //  重量级锁？ why?

//        TimeUnit.MILLISECONDS.sleep(1000); // 如果调用sleep 下面会变无锁
        int index = 0;
        while((index++)<10) {
            Thread.yield();
        }

        out.println("**** end the lock");
        out.println(layout.toPrintable()); // 上面要休息1s，后这里才无锁(调用sleep)
    }


}
