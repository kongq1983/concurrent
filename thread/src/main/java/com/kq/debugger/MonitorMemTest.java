package com.kq.debugger;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-10-14 8:38
 * @since 2020-0630
 */
public class MonitorMemTest {

    private int id = 100;

    public static void main(String[] args) throws Exception{
        MonitorMemTest m = new MonitorMemTest();
        m.id = 200;

        System.out.println("m="+m);

        Stu stu = new Stu();
        stu.setId(222);
        stu.setName("test");

        System.out.println("stu="+stu.hashCode()+"  address="+System.identityHashCode(stu));

        TimeUnit.MINUTES.sleep(10);




    }



}
