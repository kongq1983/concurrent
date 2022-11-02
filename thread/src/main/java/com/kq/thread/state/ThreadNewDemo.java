package com.kq.thread.state;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2022-11-02 16:50
 * @since 2020-0630
 */
public class ThreadNewDemo {

    public static void main(String[] args) throws Exception{

        Thread t = new Thread();

        for(int i=0;i<5;i++) {
            System.out.println(t.getState()); //NEW
            TimeUnit.SECONDS.sleep(1);
        }

    }

}
