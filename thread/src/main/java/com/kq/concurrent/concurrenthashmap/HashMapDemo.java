package com.kq.concurrent.concurrenthashmap;

import java.util.HashMap;

/**
 * @author kq
 * @date 2022-03-04 11:18
 * @since 2020-0630
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();

//        hashMap.put("1","1");
//        hashMap.put("2","2");
//        hashMap.put("3","3");

        for(int i=0;i<16;i++){
            MyDto dto = new MyDto(i);
            hashMap.put(dto,dto);
        }
        for(int i=0;i<16;i++){
            MyDto dto = new MyDto(i);
            hashMap.put(dto,dto);
        }
        for(int i=0;i<16;i++){
            MyDto dto = new MyDto(i);
            hashMap.put(dto,dto);
        }
        for(int i=0;i<100;i++){
            MyDto dto = new MyDto(i);
            hashMap.put(dto,dto);
        }

        // GMT_CREATE   GMT_MODIFIED
        // CONTACTER   DISTRICT  PROVINCE  DESCRIPTION   BUSINESS  DEPLOY  SCENE  DICTIONARY  DEVELOPER   BUSINESS_ID

        // CATEGORY   OPERATION  pm_user_role
//        pm_tenant_application
//        province   pub_tenant_operation_user  SMALLINT  authority

    }

}
