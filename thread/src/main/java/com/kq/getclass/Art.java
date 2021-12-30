package com.kq.getclass;

/**
 * @author kq
 * @date 2021-11-18 14:53
 * @since 2020-0630
 */
//Obejct类有一个getClass()方法：
//        2     返回此 Object 的运行时类。
//        3     返回的 Class 对象是由所表示类的 static synchronized 方法锁定的对象。
public class Art {
        Art() {
           System.out.println("Art");
           System.out.println(getClass().getName());
       }
}
