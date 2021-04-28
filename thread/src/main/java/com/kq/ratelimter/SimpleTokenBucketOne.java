package com.kq.ratelimter;

/**
 * @author kq
 * @date 2021-04-28 9:58
 * @since 2020-0630
 */
public class SimpleTokenBucketOne {

    /**
     * 令牌桶限流算法
     */
    public long timeStamp = System.currentTimeMillis();  // 当前时间
    public long capacity; // 桶的容量
    public long rate; // 令牌放入速度
    public long tokens; // 当前令牌数量

    public boolean grant() {
        long now = System.currentTimeMillis();
        // 先添加令牌
        tokens = Math.min(capacity, tokens + (now - timeStamp) * rate);
        timeStamp = now;
        if (tokens < 1) {
            // 若不到1个令牌,则拒绝
            return false;
        } else {
            // 还有令牌，领取令牌
            tokens -= 1;
            return true;
        }
    }

}
