package com.kq.ratelimter;

/**
 * 两个变量,一个是桶的大小,支持流量突发增多时可以存多少的水(burst),另一个是水桶漏洞的大小(rate)
 *
 * 因为漏桶的漏出速率是固定的参数,所以,即使网络中不存在资源冲突(没有发生拥塞),
 * 漏桶算法也不能使流突发(burst)到端口速率.因此,漏桶算法对于存在突发特性的流量来说缺乏效率
 *
 * @author kq
 * @date 2021-04-26 15:02
 * @since 2020-0630
 */
public class LeakyBucketOne {

    double rate; // leak rate in calls/s  出水  /1000
    /** 桶的大小 */
    double burst; // bucket size in calls  桶的大小
    long refreshTime; // time for last water refresh
    /** 当前水量(当前累积请求数) */
    double water; // water count at refreshTime

    public void refreshWater() {
        long now = System.currentTimeMillis();
        //水随着时间流逝,不断流走,最多就流干到0.
        water = Math.max(0, water - (now - refreshTime) * rate);
        refreshTime = now;
    }

    public boolean permissionGranted() {
        refreshWater();
        if (water < burst) { // 水桶还没满,继续加1
            water++;
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        double water = 10.5;

        System.out.println(water++);
        System.out.println(water++);

    }

}
