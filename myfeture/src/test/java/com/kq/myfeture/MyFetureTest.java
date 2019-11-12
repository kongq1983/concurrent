package com.kq.myfeture;

import java.util.concurrent.*;

/**
 * MyFetureTest
 *
 * @author kq
 * @date 2019-11-12
 */
public class MyFetureTest {

    static BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,3, TimeUnit.SECONDS,blockingQueue);

    public static void main(String[] args) {

        Callable<String> callable1 = ()-> {
            System.out.println("---------------------start callable1-----------");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("---------------------e-n-d callable1-----------");
            return "ok1";
        };

    }

}
