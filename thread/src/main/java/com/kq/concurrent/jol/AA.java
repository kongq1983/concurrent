package com.kq.concurrent.jol;

import com.kq.concurrent.jol.dto.MyObj;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;


public class AA {

    public static void main(String[] args) throws Exception{
//        out.println(VM.current().details());  // 加了这个，下面会偏向，不加下面不会偏向，变无锁
//        final MyObj a = new MyObj();  // 这里是无锁


//
//        System.out.println(layout.toPrintable()); // 无锁

        TimeUnit.MILLISECONDS.sleep(4100); // 4.1s
        final MyObj a = new MyObj(); // 这里是偏向锁
        ClassLayout layout = ClassLayout.parseInstance(a); // 偏向锁  要在4s之后

        out.println("**** Fresh object");
        out.println(layout.toPrintable());

        synchronized (AA.class) {
            out.println(layout.toPrintable());
        }

//        synchronized (AA.class) {
//            a.hashCode();
////            layout = ClassLayout.parseInstance(a);
//            out.println("**** After object");
//            out.println(layout.toPrintable());
//
//        }


    }

}
