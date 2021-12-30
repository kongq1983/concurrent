package com.kq;

/**
 * @author kq
 * @date 2021-09-08 8:31
 * @since 2020-0630
 */
public class VolitaleDemo {

    public volatile int number;

    public void deal(){
        Runnable runnable = ()->{
            number=3;
            System.out.println(number);
        };

        new Thread(runnable).start();

        int b = number;
        System.out.println(b);
    }

    public static void main(String[] args) {

//        synchronized(VolitaleDemo.class){
////            number = 5;
////        }
        new VolitaleDemo().deal();


    }


}
