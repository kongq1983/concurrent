package com.kq.concurrent.jol.mydemo;

import com.kq.concurrent.jol.dto.MyObj;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import static java.lang.System.out;

/**
 * @author kq
 * @date 2021-12-29 10:20
 * @since 2020-0630
 */
public class HashCodeDemo1 {

    public static void main(String[] args) {

        System.out.println(VM.current().details());  // 加了这个，下面会偏向，不加下面不会偏向，变无锁

//        MyObj a = new MyObj();
        A a = new A();
        System.out.println("initial ----------------------");
        ClassLayout layout = ClassLayout.parseInstance(a); // 偏向锁  要在4s之后

        System.out.println(layout.toPrintable());

        synchronized (a) {
            a.hashCode();
            System.out.println("After call a.hashCode()");
            System.out.println(layout.toPrintable());
        }

        // 偏向锁 -> callHashCode -> 重量级锁
        // 无锁 -> callHashCode -> 重量级锁

        System.out.println("end  -----------------------");
        out.println("a hashCode: " + Integer.toHexString(a.hashCode()));
        System.out.println(layout.toPrintable());


    }

    public static class A {
        // no fields
    }

}
