package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-12-29 10:44
 * @since 2020-0630
 */
public class WaitDemo1 {


    public static void main(String[] args) throws Exception{
        System.out.println(VM.current().details());

        HashCodeDemo1.A a = new HashCodeDemo1.A();
        ClassLayout layout = ClassLayout.parseInstance(a);
        System.out.println("initial ----------------------");
        System.out.println(layout.toPrintable()); // 偏向锁

        Runnable runnable = ()->{
            int index = 0;
            while (index<2) {
                System.out.println("child thread ----------------------");
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
            }
        };

        new Thread(runnable).start();

        synchronized (a) {
            a.wait();
        }

        System.out.println("end ----------------------");
        System.out.println(layout.toPrintable()); // 无锁

    }

}
