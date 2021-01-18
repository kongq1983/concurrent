package com.kq.concurrent.lock;

public class WaitNotifyDeadLockDemo {

    public static void main(String[] args) throws Exception{
        // 死锁
        new WaitNotifyDeadLockDemo().deadlockDemo();
    }

    Object object = null;

    public void deadlockDemo() throws Exception {

        new Thread(()->{
            while (object==null) {
                    System.out.println("小朋友拿到锁......."+this);
                    try {
                        Thread.sleep(5000L);  // 通知的时候，还在睡觉，wait唤不醒
                        System.out.println("没有冰淇淋，小朋友不开心，等待......");
                        synchronized (this) {
                            System.out.println("after synchronized (this) ");
                            this.wait();  // 这里一直阻着
                        }
                        System.out.println("=============================after wait");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
            }
            System.out.println("小朋友买到冰淇淋，开心回家");
        }).start();

        Thread.sleep(3000L);
        object = new Object();

        synchronized (this) {
            System.out.println("店员拿到锁......."+this);
            this.notifyAll(); // 3s先通知
            System.out.println("通知小朋友");
        }
        // 死锁，没释放锁
        // 3点唤醒  6点睡觉   这样死锁

    }
}
