package com.kq.thread.state;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2022-11-02 16:50
 * @since 2020-0630
 */
public class ThreadTerminatedDemo {

    public static void main(String[] args) throws Exception{

        Runnable r = ()->{

            for(int i=0;i<Integer.MAX_VALUE;i++) {
//                Thread.yield(); // 注释本句，会打印下面这句话is end.    线程状态变为TERMINATED
                if(i==Integer.MAX_VALUE-1){
                    System.out.println(Thread.currentThread()+",is end.");
                }
            }

        };

        Thread t = new Thread(r);
        t.setDaemon(true);

        for(int i=0;i<8;i++) {

            if(i==4){
                t.start();
            }

            System.out.println(t.getState()); //NEW
            TimeUnit.SECONDS.sleep(1);

        }

    }

}
