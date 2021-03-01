package com.kq.concurrent.env;

import java.util.Map;

/**
 * EnvDemo
 *
 * @author kq
 * @date 2019-05-25
 */
public class EnvDemo {

    public static void main(String[] args) {

        Map<String, String> map = System.getenv();
        System.out.println(map);
        System.out.println(map.get("TMP"));

    }

}
