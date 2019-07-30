package com.kq.concurrent.mydemo;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * CodeCheckExistsDemo
 * 判断编码重复
 * @author kq
 * @date 2019-07-30
 */
public class CodeCheckExistsDemo {

    private static ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

    private static final int size = 30000;

    public static void main(String[] args) throws Exception{

        CountDownLatch latch = new CountDownLatch(size);


        Runnable r = ()-> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
            String paysign = sdf.format(new Date()) + RandomStringUtils.randomAlphabetic(4);
            map.putIfAbsent(paysign,paysign);
//            System.out.println("----------------------------- map.size = "+map.size());
            latch.countDown();
        };

        Thread[] ts = new Thread[size];

        for(int i=0;i<ts.length;i++) {
            ts[i] =  new Thread(r);
        }

        for(Thread t : ts) {
            t.start();
        }

        latch.await();

        System.out.println("----------------------------- map.size = "+map.size());

        for(Map.Entry<String,String> kv : map.entrySet()) {
            System.out.println(kv.getKey());
        }

        System.out.println("----------------------------- map.size = "+map.size());

    }



}
