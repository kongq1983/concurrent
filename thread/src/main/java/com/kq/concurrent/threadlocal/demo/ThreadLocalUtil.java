package com.kq.concurrent.threadlocal.demo;

import java.util.Date;

/**
 * @author kq
 * @date 2020-07-30 11:16
 * @since 2020-0630
 */
public class ThreadLocalUtil {

    private static ThreadLocal<LoginDto> threadLocal = new ThreadLocal();

    public static void set(Long userId){
        LoginDto dto = new LoginDto();
        dto.setUserId(userId);
        dto.setLoginTime(new Date());
        threadLocal.set(dto);
    }

    public static LoginDto getLoginDto(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }

}
