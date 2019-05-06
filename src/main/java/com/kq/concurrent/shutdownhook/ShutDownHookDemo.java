package com.kq.concurrent.shutdownhook;

/**
 * ShutDownHookDemo
 *
 * @author kq
 * @date 2019-05-06
 */
public class ShutDownHookDemo {


    public static void main(String[] args) throws Exception{

        register();

        Thread.sleep(3000);

        unregister();

    }


    public static void register() {
        Runtime.getRuntime().addShutdownHook(MyShutdownHook.getMyShutdownHook());
    }

    /**
     * Unregister the ShutdownHook
     */
    public static void unregister() {
        Runtime.getRuntime().removeShutdownHook(MyShutdownHook.getMyShutdownHook());
    }

}
