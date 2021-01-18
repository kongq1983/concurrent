package com.kq.concurrent.lock;

/**
 * 死锁例子
 */
public class SuspendResumeDemo {

    public static void main(String[] args) throws Exception{
        // 能正常演示
        new SuspendResumeDemo().demo();

    }

    Object object = null;

    public void demo() throws Exception {


        Thread thread = new Thread(()->{
            while (object==null) {
//                synchronized (WaitNotifyDemo.class) {
                System.out.println("小朋友拿到锁......."+this);

                try {
                    System.out.println("没有冰淇淋，小朋友不开心，等待......");
                    System.out.println("-------------start wait------------------");
                    synchronized (this) {
                        System.out.println("before suspend synchronized (this) ");
                        Thread.currentThread().suspend();
                        System.out.println("after suspend synchronized (this) ");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            System.out.println("小朋友买到冰淇淋，开心回家");
        });

        thread.start();

        Thread.sleep(3000L);
        object = new Object();
        System.out.println("============================================");
//        synchronized (WaitNotifyDemo.class) {
        synchronized (this) {
            // 这里进不来的  suspend  没释放锁
            System.out.println("店员拿到锁......."+this);
            System.out.println("before resume synchronized (this) ");
            thread.resume();
            System.out.println("after resume synchronized (this) ");
            System.out.println("通知小朋友");
        }
        // 死锁，没释放锁
        // 3点唤醒  6点睡觉   这样死锁

    }

}
