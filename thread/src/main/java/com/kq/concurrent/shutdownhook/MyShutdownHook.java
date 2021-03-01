package com.kq.concurrent.shutdownhook;

/**
 * MyShutdownHook
 *
 * @author kq
 * @date 2019-05-06
 */
public class MyShutdownHook extends Thread{


    public MyShutdownHook(String name){
        super(name);
    }


    private static final MyShutdownHook myShutdownHook = new MyShutdownHook("MyShutdownHook");


    public static MyShutdownHook getMyShutdownHook(){
        return myShutdownHook;
    }



    @Override
    public void run(){
        System.out.println("MyShutdownHook execute.");
    }

}
