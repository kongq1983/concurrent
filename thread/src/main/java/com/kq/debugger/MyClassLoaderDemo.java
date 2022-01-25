package com.kq.debugger;

/**
 * @author kq
 * @date 2022-01-07 16:33
 * @since 2020-0630
 */
public class MyClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoader classLoader =  ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);
    }

}
