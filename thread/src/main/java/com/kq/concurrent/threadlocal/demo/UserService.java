package com.kq.concurrent.threadlocal.demo;

/**
 * @author kq
 * @date 2020-07-30 11:15
 * @since 2020-0630
 */
public class UserService {


    public void doIt() {

        LoginDto loginDto =  ThreadLocalUtil.getLoginDto();

        System.out.println(Thread.currentThread().getName()+",loginDto="+loginDto);

    }

}
