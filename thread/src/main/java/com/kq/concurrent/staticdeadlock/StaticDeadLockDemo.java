package com.kq.concurrent.staticdeadlock;

/**
 * 不一定会发生 有时发生  有时不会发生
 * InstanceKlass::initialize_impl Step2  instanceKlass.cpp:840
 * @author kq
 * @date 2022-01-06 17:52
 * @since 2020-0630
 */
public class StaticDeadLockDemo {

    public static void main(String[] args) {
        new Thread(()-> new A()).start();
        new Thread(()-> new B()).start();
    }


}

class A {
    static {

        System.out.println("start Class A cinit"); // 发生死锁，只会打印这一句
        new B();
        System.out.println("e-n-d Class A cinit");
    }
}

class B {
    static {
        System.out.println("start Class B cinit"); // 发生死锁，只会打印这一句
        new A();
        System.out.println("e-n-d Class B cinit");

    }
}