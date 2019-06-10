package com.kq;

/**
 * RunRepeatDemo
 *
 * @author kq
 * @date 2019-06-05
 */
public class RunRepeatDemo {

    public static void main(String[] args) {


        Runnable r = ()->{
            long index = 0;
           while (true){
               System.out.println("index="+index);
               if(index%10000==0) {
                   try {
                       Thread.sleep(100);
                   }catch (Exception e){
                       e.printStackTrace();;
                   }
               }
               index++;
           }
        };


        Thread t = new Thread(r,"run-repeat-thread");
        t.start();


    }

}
