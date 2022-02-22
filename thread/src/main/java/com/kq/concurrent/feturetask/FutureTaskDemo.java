package com.kq.concurrent.feturetask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author kq
 * @date 2022-02-22 16:29
 * @since 2020-0630
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws Exception{

        Callable<String> onlineShopping = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待送货");

                int num = 1;
                while (true) {
                    Thread.sleep(1000);  // 模拟送货时间
                    System.out.println("-----------------------num="+num);
                    num++;
                    if(num>10)break;
                }

                System.out.println("第一步：快递送到");
                return "ok";
            }
        };


        FutureTask<String> task = new FutureTask<String>(onlineShopping);
        new Thread(task).start();

        // 谁调用 谁阻塞
        String result = task.get();

        System.out.println(result);



    }

}
