package com.kq.ratelimter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kq
 * @date 2021-04-26 17:12
 * @since 2020-0630
 */
public class Test {

    public static void main(String[] args) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date currentDate = sdf.parse(sdf.format(new Date()));
        if(currentDate.getTime() < sdf.parse("18:16").getTime());

        System.out.println(sdf.parse("18:16"));
        System.out.println(sdf.parse("18:16").getTime());

        System.out.println(((int)(100-0.0001)));
        System.out.println(((int)(100-0.001)));
        System.out.println(((int)(100-0.00001)));
        System.out.println(((int)(100-0.0000001)));

    }

}
