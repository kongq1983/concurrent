package com.kq.concurrent.concurrenthashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 覆盖率HashMap的 newNode
 * LinkedHashMap.Entry<K,V> head;
 * LinkedHashMap.Entry<K,V> tail;
 * @author kq
 * @date 2022-02-23 14:58
 * @since 2020-0630
 */
public class LinkedHashMapDemo {

    public LinkedHashMapDemo(){
        MyDto dto = new MyDto(1);
        System.out.println(dto);
    }


    public static void main(String[] args) {
       Map<String,Integer> map = new LinkedHashMap(); // transient LinkedHashMap.Entry<K,V> head;
//       Map<String,Integer> map = new HashMap();
        // newNode
        map.put("tne",1);
        map.put("one",2);
        map.put("three",3);
        map.put("tne",5);

//        tne   // 这个是第一个 ，不是最后1个  Node 只会创建1次
//        one
//        three

        MyDto dto = new MyDto(1);

        for(Map.Entry<String, Integer> kv : map.entrySet()) {
            System.out.println(kv.getKey() +"  value="+kv.getValue());
        }


    }

}
