package com.kq.memory;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author kq
 * @date 2021-11-17 16:56
 * @since 2020-0630
 */
public class MainDemo {

    public static void main(String[] args) {

        A a = new A();
        a.getClass();

        print(ClassLayout.parseInstance(a).toPrintable());

        B b = new B();
        print(ClassLayout.parseInstance(b).toPrintable());

    }


    static void print(String message) {
        System.out.println(message);
        System.out.println("-------------------------");
    }


}
