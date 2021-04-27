package com.kq.ratelimter;

/**
 * 漏桶限流算法
 * @author kq
 * @date 2021-04-26 14:41
 * @since 2020-0630
 */
public class LeakyBucket {

    public long timeStamp = System.currentTimeMillis();  // 当前时间
    public long capacity; // 桶的容量 500
    public long rate; // 水漏出的速度(每秒系统能处理的请求数)       假设500/1000  每秒出水量
    public double water; // 当前水量(当前累积请求数)


//    整个算法其实十分简单。首先，我们有一个固定容量的桶，有水流进来，也有水流出去。对于流进来的水来说，
//    我们无法预计一共有多少水会流进来，也无法预计水流的速度。但是对于流出去的水来说，
//    这个桶可以固定水流出的速率。而且，当桶满了之后，多余的水将会溢出。
    public boolean limit() {
        long now = System.currentTimeMillis();
        //水随着时间流逝,不断流走,最多就流干到0  比如1s  每毫秒都在滴水  rate每秒漏水
        water = Math.max(0, water - ((now - timeStamp)/1000) * rate); // 先执行漏水，计算剩余水量
        //  now = timeStamp = 0
        // now - timeStamp = 1000 /1000  * 500

        // 100/1000 * 500 = 50
        // 200/1000 * 500 = 100
        // 300/1000 * 500 = 150
//        .............
        // 1000/1000 * 500 = 500



        timeStamp = now;
        if ((water + 1) < capacity) {
            // 尝试加水,并且水还未满
            water += 1;
            return true;
        } else {
            // 水满，拒绝加水
            return false;
        }
    }
}