package com.kq.ratelimter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 计数器算法
 * @author kq
 * @date 2021-04-26 11:05
 * @since 2020-0630
 */
public class Counter {

    /** 开始时间 */
    private AtomicLong startTime = new AtomicLong(System.currentTimeMillis());

    /** 计数器 */
    private AtomicLong count = new AtomicLong(0);

    /** 单位时间内 最大请求数 */
    private int limit = 10000;

    /**  时间窗口ms */
    private long interval = 1000 * 2;

    /**  只用于统计 */
    private Map<String,Long> staticMap = new ConcurrentHashMap();

    public boolean tryAccquire(){

        long nowTime = System.currentTimeMillis();

        if (startTime.get()+interval > nowTime) {  //在时间窗口内

            count.set(count.get()+1);

            if(count.get()>limit) {
                stat("false");
                return false;
            }

            stat("true1");
            return true;
        } else {
            startTime.set(System.currentTimeMillis());
            count.set(1);
            stat("true2");
            return true;
        }

    }

    private void stat(String key) {

        staticMap.compute(key,(k,v)->{
            Long temp;
            if(v==null){
                temp = 0l;
            }else {
                temp = v;
            }
            return ++temp;
        });

    }

    public Map<String,Long> getStaticMap() {
        return staticMap;
    }

}
