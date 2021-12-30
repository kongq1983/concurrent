package com.kq.blocking;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kq
 * @date 2021-06-28 8:58
 * @since 2020-0630
 */
public class ArrayListBlockingDemo {
    static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws Exception{
        ArrayBlockingQueue<String> blocking = new ArrayBlockingQueue(2);

        Runnable putRun = ()-> {
            try {
//                TimeUnit.SECONDS.sleep(3);

                String value = String.valueOf(atomicInteger.incrementAndGet());
                System.out.println(LocalDateTime.now()+"---,"+Thread.currentThread().getName()+", before get value: "+value);
                blocking.put(value);
                System.out.println(LocalDateTime.now()+"---,"+Thread.currentThread().getName()+", after get value: "+value);
                TimeUnit.HOURS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();;
            }

        };

        Thread[] putThreads = new Thread[5];

        for(int i=0;i<putThreads.length;i++) {
            putThreads[i] = new Thread(putRun,"put-thread-"+i);
            putThreads[i].start();
        }




        Runnable takeRun = ()-> {
            try {
                TimeUnit.SECONDS.sleep(3);
                String value = blocking.take();
                System.out.println(LocalDateTime.now()+"###,"+Thread.currentThread().getName()+", get value: "+value);
            }catch (Exception e){
                e.printStackTrace();;
            }

        };

        TimeUnit.SECONDS.sleep(3);

        Thread[] takeRuns = new Thread[5];

        for(int i=0;i<takeRuns.length;i++) {
            takeRuns[i] = new Thread(takeRun,"take-thread-"+i);
//            takeRuns[i].start();
        }




        TimeUnit.MINUTES.sleep(10);



    }

}
