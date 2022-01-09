package com.kq.concurrent.concurrenthashmap;

/**
 *
 * java -server -XX:PrintAssembly -cp.VolatileDemo
 * -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*VolatileDemo.signDeal
 * @author kq
 * @date 2022-01-05 19:24
 * @since 2020-0630
 */
public class VolatileDemo {

    public volatile  int sign;

    public void signDeal(){
        sign++;
    }

    public static void main(String[] args) {
        new VolatileDemo().signDeal();
//        System.out.println(num);
    }

}
