package com.kq.concurrent.feturetask;

import java.util.concurrent.Callable;

/**
 * MyFetureTaskTest
 *
 * @author kq
 * @date 2019/5/23
 */
public class MyFetureTaskTest {

    public static void main(String[] args) throws Exception{
        Callable callable = ()-> {
            System.out.println("-----------start----------------");
            Thread.sleep(5000l);
            System.out.println("-----------e-n-d----------------");
            return "end";
        };

        MyFetureTask fetureTask = new MyFetureTask(callable);

        new Thread(fetureTask).start();

        System.out.println("------1111----------------");
        System.out.println("result="+fetureTask.get());
        System.out.println("------2222----------------");
    }

}
