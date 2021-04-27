package com.kq.ratelimter;

import java.util.LinkedList;
import java.util.Random;

/**
 * 滑动时间窗口
 * @author kq
 * @date 2021-04-26 13:54
 * @since 2020-0630
 */
public class SlidingTimeWindow {

    //服务访问次数，可以放在Redis中，实现分布式系统的访问计数
    Long counter = 0L;

    //使用LinkedList来记录滑动窗口的10个格子。
    LinkedList<Long> slots = new LinkedList<Long>();

    public static void main(String[] args) throws InterruptedException {
        SlidingTimeWindow timeWindow = new SlidingTimeWindow();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    timeWindow.doCheck();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true){
            //TODO 判断限流标记
            timeWindow.counter++;
            Thread.sleep(new Random().nextInt(15));
        }
    }

    private void doCheck() throws InterruptedException {
        while (true) {
            slots.addLast(counter);
            if (slots.size() > 10) {
                slots.removeFirst();
            }
            //比较最后一个和第一个，两者相差100以上就限流
            if ((slots.peekLast() - slots.peekFirst()) > 100) {
                System.out.println("限流了。。");
                //TODO 修改限流标记为true
            }else {
                //TODO 修改限流标记为false
            }

            Thread.sleep(100);
        }
    }


}
