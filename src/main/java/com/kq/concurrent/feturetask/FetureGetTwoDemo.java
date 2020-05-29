package com.kq.concurrent.feturetask;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.*;

/**
 * FetureGetTwoDemo
 * 除了第一次get()阻塞外，后面再调用get()直接获取返回值
 * @author kq
 * @date 2019-11-13
 */
public class FetureGetTwoDemo {


    static BlockingQueue blockingQueue = new ArrayBlockingQueue(100);
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,3, TimeUnit.SECONDS,blockingQueue);


    public static void main(String[] args) throws Exception{

        StopWatch stopWatch = new StopWatch();

        Callable<String> callable2 = ()-> {
            System.out.println("---------------------start callable2-----------");
            TimeUnit.SECONDS.sleep(1300);
//            if(callable1!=null) throw new RuntimeException("---------------------abcd--------------------");
            System.out.println("---------------------e-n-d callable2-----------");
            return "ok2";
        };

        System.out.println("");
        Future<String> f1 = threadPoolExecutor.submit(callable2);
        stopWatch.start();
        String result1 = f1.get();
        stopWatch.stop();
        System.out.println("result1="+result1+" spent time="+stopWatch);

        stopWatch.reset();

        stopWatch.start();
        String result2 = f1.get();
        stopWatch.stop();
        System.out.println("result2="+result2+" spent time="+stopWatch);
        String result3 = f1.get();
        System.out.println("result3="+result3);

        threadPoolExecutor.shutdown();

    }


}
