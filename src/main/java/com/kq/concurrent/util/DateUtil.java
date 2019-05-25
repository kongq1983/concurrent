package com.kq.concurrent.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil
 * @author kq
 * @date 2019-05-25
 */
public class DateUtil {

    /**
     * 开始
     * @return
     */
    public static String getNowTime(){

        SimpleDateFormat aDate=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

        return aDate.format(new Date());

    }

}
