package com.kq.concurrent.concurrenthashmap;

/**
 * @author kq
 * @date 2022-01-07 15:38
 * @since 2020-0630
 */
public interface INameService {

    // static final 由于 NUM是final修饰 所以不会生成clinit
    int NUM = 100;

    // 接口只有static final



    default String getNUM() {
        return "";
    }

}
