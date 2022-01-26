package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * @author kq
 * @date 2022-01-26 11:28
 * @since 2020-0630
 */
public class NoLockToHeavyLockByWait {

    public static void main(String[] args) throws Exception{

//        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms

        final NoLockToLightBiasedLock.A a = new NoLockToLightBiasedLock.A();
        ClassLayout layout = ClassLayout.parseInstance(a);



        out.println("**** Before object");
        out.println(layout.toPrintable());  // 无锁




        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 无锁

        monitorObj( layout ,a);

        synchronized (a) {
            a.wait();
            out.println("**** With the lock");
            out.println(layout.toPrintable()); // wait()直接重量级锁
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable()); // markword是重量级锁标志

    }

    public static void monitorObj(ClassLayout layout ,Object a){
        Runnable runnable = ()->{
            int index = 0;
            while (index<2) {
                System.out.println("child thread print ----------------------");
                System.out.println(layout.toPrintable()); // 重量级锁
                try {
                    TimeUnit.SECONDS.sleep(2);
                    index++;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (a) {
                a.notify();
                System.out.println("notify a ----------------------");
            }
        };

        new Thread(runnable).start();
    }

}
