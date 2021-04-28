package com.kq.ratelimter;

/**
 *
 * 以下是bucket4j给出的一个简单实现，用于理解token bucket算法
 * 这个算法没有采用线程去refill token，因为bucket太多的话，线程太多，耗cpu
 * 这个算法没有存储每个period使用的token，设计了lastRefillTimestamp字段，用于计算需要填充的token
 * 每次tryConsume的时候，方法内部首先调用refill，根据设定的速度以及时间差计算这个时间段需要补充的token，更新availableTokens以及lastRefillTimestamp
 * 之后限流判断，就是判断availableTokens与请求的numberTokens
 * 小结
 * token bucket算法，是基于qps来限流，其简单的实现，就是计算单位时间补充token的速率，然后每次tryConsume的时候根据速率修正availableTokens。
 *
 * @author kq
 * @date 2021-04-28 9:22
 * @since 2020-0630
 */
public class SimpleTokenBucket {
    /** 令牌桶大小 */
    private final long capacity;
    /** 每毫秒填充令牌数  (double) refillTokens / (double) refillPeriodMillis */
    private final double refillTokensPerOneMillis;

    private double availableTokens;
    private long lastRefillTimestamp;

    /**
     * Creates token-bucket with specified capacity and refill rate equals to refillTokens/refillPeriodMillis
     */
    public SimpleTokenBucket(long capacity, long refillTokens, long refillPeriodMillis) {
        this.capacity = capacity;
        this.refillTokensPerOneMillis = (double) refillTokens / (double) refillPeriodMillis;

        this.availableTokens = 1;
//        this.availableTokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    synchronized public boolean tryConsume(int numberTokens) {
        refill();
        if (availableTokens < numberTokens) {
            return false;
        } else {
            availableTokens -= numberTokens;
            return true;
        }
    }

    /** 先填充  */
    private void refill() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > lastRefillTimestamp) { // 毫秒差
            long millisSinceLastRefill = currentTimeMillis - lastRefillTimestamp; // 毫秒差
            double refill = millisSinceLastRefill * refillTokensPerOneMillis; // 每毫秒填充数* 总共过来多少秒 (下面这个如果>capacity 则取capacity)
            this.availableTokens = Math.min(capacity, availableTokens + refill); // 可获取的令牌
            this.lastRefillTimestamp = currentTimeMillis;
        }
    }


    public static void main(String[] args) {
        // 100 tokens per 1 second  每毫秒0.1个   secnod * ((double) refillTokens / (double) refillPeriodMillis) + 初始的availableTokens
        // 假设每s100 则1s能跑200   2s能跑300  因为初始化的初始的availableTokens = 100
        SimpleTokenBucket limiter = new SimpleTokenBucket(100, 100, 1000);

        long startMillis = System.currentTimeMillis();
        long consumed = 0;
        while (System.currentTimeMillis() - startMillis < 5000) {
            if (limiter.tryConsume(1)) {
                consumed++;
            }
        }
        System.out.println(consumed);
    }





}
