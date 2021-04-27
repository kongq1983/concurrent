package com.kq.ratelimter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kq
 * @date 2021-04-26 16:52
 * @since 2020-0630
 */
public class LeakyBucketTwo {
    /*** 水漏出的速度 每毫秒出水量 (每秒系统能处理的请求数  rate*1000) */
    double rate; // leak rate in calls/s  出水  /1000
    /** 桶的大小 */
    double burst; // bucket size in calls  桶的大小
    /** 刷新时间  */
    long refreshTime; // time for last water refresh
    /** 当前水量(当前累积请求数) */
    int water; // water count at refreshTime

    private static AtomicLong atomicSucc = new AtomicLong(0);

    private Object lock = new Object();

    public LeakyBucketTwo(double rate, double brust) {
        this.rate = rate;
        this.burst = brust;
    }


    /**
     * 刷新
     */
    public void refreshWater() {

        synchronized (lock) {
            long now = System.currentTimeMillis();
            //水随着时间流逝,不断流走,最多就流干到0.
            long diff = (now - refreshTime);
            water = Math.max(0, (int) (water - diff * rate));  // rate >0 and <=1 其实都是相当于 water - 1
            if (water < burst) {
                System.out.println("water=" + water + "  diff=" + diff + " succ.size=" + atomicSucc.get());
            }
            refreshTime = now;
        }

        // diff =0   water一直累积到100   water=1  diff=0 water=2  diff=0 water=3  diff=0  ..........   water=100  diff=0


    }

    /**
     * 获取令牌
     * @return
     */
    public boolean getAccquire() {
        refreshWater();
        if (water < burst) { // 水桶还没满,继续加1
            water++;
            atomicSucc.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) throws Exception {
        // 1毫秒 100个
//        LeakyBucketTwo leakyBucket = new LeakyBucketTwo(1,100);
        // rate=1 将近5200
        // 1s = 1000ms
        // rate =0    到达brust后　　永远是brust
        // rate([0-1直接) (0-1 之间差不多，大于0)  一般都是 1 * 2s = 将近2000+brust  这个是由于(int)原因  都当作1ms处理
        // 5000/1000  burst=100   相当于 5000/1000*1000 * 5s = 将近25000
        // 2000/1000  burst=100   相当于 2*1000 * 2s + burst= 将近4000+burst
        // 3000/1000  burst=100   相当于 3*1000 * 2s + burst= 将近6000+ burst
        // 5000/1000  burst=100   相当于 5*1000 * 2s + burst= 将近10000+ burst

        // import ! import ! import ! import ! import !
        // 总结: rate =0  到达容量后，用于是最大容量　
        // >0 and <=1   经过(int)后，相当于都是1ms
        // 除了0之外 统一公式  (将近) = 1ms * 2*10000 + brust = (将近)

        // (0-1 之间差不多，大于0)  一般都是 1 * 2s = 将近2000+brust
        LeakyBucketTwo leakyBucket = new LeakyBucketTwo(3,200);
        // 算法 1000 * rate(1) + 100  = 将近2100
//        LeakyBucketTwo leakyBucket = new LeakyBucketTwo(1,100);

//        ExecutorService executorService = Executors.newFixedThreadPool(2);

//        for(int i=0;i<1000;i++) {
//            executorService.execute(()->{
//                leakyBucket.getAccquire();
//            });
//        }


        long startTime = System.currentTimeMillis();

        long internelMs = 2000;

        for(long i=0;i<Long.MAX_VALUE;i++) {

            long endTime = System.currentTimeMillis();
//        executorService.execute(()->{
            if((endTime-startTime)<=internelMs) {
                leakyBucket.getAccquire();
            }else {

                System.out.println("endTime-startTime="+(endTime-startTime));
                break;
            }
        }

        System.out.println("final atomicSucc = "+atomicSucc.get());


//        });


//        executorService.awaitTermination(2, TimeUnit.MINUTES);
//        executorService.shutdown();

    }



}
