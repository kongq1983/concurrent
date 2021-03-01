package com.kq.concurrent.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-03-01 17:27
 * @since 2020-0630
 */
public class PropertiesUtil {

    public static boolean isLock() {

        Properties properties = getDefaultProperties();

        String lockStr = properties.getProperty("lock");
        lockStr = lockStr==null?"":lockStr.trim();
        System.out.println("lockStr="+lockStr);
        return lockStr.endsWith("1") || lockStr.toLowerCase().equals("true");

    }

    private static Properties getDefaultProperties() {

        Properties properties = new Properties();
        try( InputStream in = PropertiesUtil.class.getResourceAsStream("/application.properties");) {

            properties.load(in);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


    public static void main(String[] args) throws Exception{

        for(int i=0;i<100;i++) {
            System.out.println(isLock());
            TimeUnit.SECONDS.sleep(2);
        }

    }

}
