package com.kq.concurrent.concurrenthashmap;

/**
 * @author kq
 * @date 2022-01-04 14:11
 * @since 2020-0630
 */
public class MyDto {

    private int i;

    public MyDto(int i) {
        this.i = i;
    }


    @Override
    public int hashCode() {
//        return 1;

        if(i<100){
            return (i%100);
        } else {
            return 1;
        }


    }

    @Override
    public String toString() {
        return "MyDto{" +
                "i=" + i +
                '}';
    }
}
