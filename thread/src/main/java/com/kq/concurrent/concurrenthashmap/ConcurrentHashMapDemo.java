package com.kq.concurrent.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author kq
 * @date 2022-01-04 9:14
 * @since 2020-0630
 */
public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

//        ConcurrentHashMap hashMap = new ConcurrentHashMap(20);
        ConcurrentHashMap hashMap = new ConcurrentHashMap();

        for(int i=0;i<1000;i++) {


            MyDto myDto = new MyDto(i);

            if(i>900) {
                hashMap.put(myDto,String.valueOf(i));
            }else {
                hashMap.put(myDto,String.valueOf(i));
            }

        }

        hashMap.put("1","one");
        hashMap.put("1","two");

        System.out.println(hashMap);
    }




}
