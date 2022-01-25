package com.kq.concurrent.jol;

import com.kq.concurrent.jol.dto.MyObj;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author kq
 * @date 2022-01-22 8:58
 * @since 2020-0630
 */
public class SynchronizedHashCodeBeforeBiasedLock {

    public static void main(String[] args) {

        final MyObj a = new MyObj();  // 这里是无锁
        ClassLayout layout = ClassLayout.parseInstance(a);
        a.hashCode();
        System.out.println(layout.toPrintable()); // 无锁

        synchronized (a){
            System.out.println(layout.toPrintable()); // 轻量级锁
        }



    }



}
