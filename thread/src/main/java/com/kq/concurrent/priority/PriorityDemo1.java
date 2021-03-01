package com.kq.concurrent.priority;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by qikong on 2019/5/12.
 */
public class PriorityDemo1 {

    public static void main(String[] args) {

        Runnable r = ()-> {
//            System.out.printf(" %s start run \n ",Thread.currentThread().getName());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s run end ,priority=%s,\n",Thread.currentThread().getName(),Thread.currentThread().getPriority()+"");

        };


        List<Integer> prioritys = new ArrayList<>();
        for(int i=0;i<10;i++){
            prioritys.add(i+1);
        }


        Thread[] ts = new Thread[100];

        for(int i=0;i<100;i++) {

            ts[i] = new Thread(r, "thread-"+i);

            int priorityPos = i%10;
            ts[i].setPriority(prioritys.get(priorityPos));
        }

        for(Thread t : ts)
            t.start();

    }

}
