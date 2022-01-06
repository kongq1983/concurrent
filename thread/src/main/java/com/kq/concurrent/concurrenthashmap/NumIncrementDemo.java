package com.kq.concurrent.concurrenthashmap;

/**
 * -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*NumIncrementDemo.increment*
 * @author kq
 * @date 2022-01-04 14:53
 * @since 2020-0630
 */
public class NumIncrementDemo {

    public volatile  int sign;

    public void signDeal(){
        sign++;
    }

    public static void main(String[] args) {

        NumIncrementDemo numIncrementDemo = new NumIncrementDemo();
//        numIncrementDemo.increment1();
//        numIncrementDemo.increment2();

        System.out.println(numIncrementDemo.increment1());
        System.out.println(numIncrementDemo.increment2());
        System.out.println(numIncrementDemo.increment11()); // 0
        System.out.println(numIncrementDemo.increment22()); // 1

    }

    public int increment1() {
        int i = 0;
        i++;
        return i;
    }

    public int increment2() {
        int i = 0;
        ++i;
        return i;
    }

    public int increment11() {
        int i = 0;
        i=i++;
        return i;
    }

    public int increment22() {
        int i = 0;
        i=++i;
        return i;
    }

    public int incrementaa() {

        int j = 0;
        for(int i=0;i<10;i++) {

        }
        return j;

    }


}
