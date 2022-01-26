package com.kq.concurrent.jol.mydemo;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/** -XX:+TraceMonitorInflation
 * 偏向锁 -> 锁测消 -> 轻量级锁
 * @author kq
 * @date 2022-01-25 9:42
 * @since 2020-0630
 */
public class BiasedLockToLightWeightBySelfHashCode {


    public static void main(String[] args) throws Exception{

        TimeUnit.MILLISECONDS.sleep(4100);  // 4s之后  是偏向锁  这里多100ms

//        final BiasedLockToLightWeightBySelfHashCode.A a = new BiasedLockToLightWeightBySelfHashCode.A();
        final BiasedLockToLightWeightBySelfHashCode.AA a = new BiasedLockToLightWeightBySelfHashCode.AA();
        ClassLayout layout = ClassLayout.parseInstance(a);



        out.println("**** Before object");
        out.println(layout.toPrintable());  // 偏向锁




        out.println("**** Fresh object");
        out.println(layout.toPrintable()); // 偏向锁

//        a.hashCode();  // 会撤销偏向锁，变无锁

        out.println("**** after Call hashCode");
        out.println(layout.toPrintable()); // 无锁

        synchronized (a) {
            a.hashCode();  // 自定义的hashcode (相当于普通函数) 下面会变偏向锁
            out.println("**** With the lock");
            out.println(layout.toPrintable()); // synchronized之前调用hashcode ，则这里是轻量级锁
        }

        out.println("**** After the lock");
        out.println(layout.toPrintable()); // 无锁
    }


    public static class A {
        // no fields
    }

    public static class AA {
        private int a;
        // no fields

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AA aa = (AA) o;
            return a == aa.a;
        }

        @Override
        public int hashCode() {
            return a;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }


}
