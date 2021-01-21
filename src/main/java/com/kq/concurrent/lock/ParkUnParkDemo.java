package com.kq.concurrent.lock;

import java.util.concurrent.locks.LockSupport;

public class ParkUnParkDemo {

    public static void main(String[] args) throws Exception{
        // 能正常演示
        new ParkUnParkDemo().parkUnPark();

    }

    Object object = null;

    public void parkUnPark() throws Exception {


        Thread thread = new Thread(()->{
            while (object==null) {
//                synchronized (WaitNotifyDemo.class) {
                System.out.println("小朋友拿到锁......."+this);

                try {
                    System.out.println("没有冰淇淋，小朋友不开心，等待......");
                    LockSupport.park();
//                    System.out.println("-------------start wait------------------");
//                    synchronized (this) {
//                        this.wait();
//                    }
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
        LockSupport.unpark(thread);
        // 死锁，没释放锁
        // 3点唤醒  6点睡觉   这样死锁

    }

}
