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

        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms

        final NoLockToLightBiasedLock.A a = new NoLockToLightBiasedLock.A();
        ClassLayout layout = ClassLayout.parseInstance(a);



        out.println("**** Before object");
        out.println(layout.toPrintable());  // 偏向锁




        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁


        synchronized (a) {
            a.hashCode();
            out.println("**** With the lock");
            out.println(layout.toPrintable()); // synchronized里面调用hashcode ，则这里是重量级锁
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable()); //  重量级锁？ why?

        TimeUnit.MILLISECONDS.sleep(1000); //

        out.println("**** end the lock");
        out.println(layout.toPrintable()); // 上面要休息1s，后这里才无锁
    }


}
