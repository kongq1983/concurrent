package com.kq.concurrent.visibility;

import java.util.concurrent.TimeUnit;

/**
 * Created by qikong on 2019/5/19.
 */
public class VisibilityDemo {

    int i=0;
//    boolean isRunning = true;
    volatile boolean isRunning = true;

    public static void main(String[] args) throws Exception{

        VisibilityDemo demo = new VisibilityDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(demo.isRunning){
                    demo.i++;
                }

                // 不加volatile 有可能不会打印 这句
                System.out.println("i="+demo.i);
            }
        }).start();

        TimeUnit.SECONDS.sleep(3);
        demo.isRunning = false;

    }



}
