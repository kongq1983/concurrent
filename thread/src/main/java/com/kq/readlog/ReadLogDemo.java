package com.kq.readlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author kq
 * @date 2020-12-15 18:15
 * @since 2020-0630
 */
public class ReadLogDemo {

    public static void main(String[] args) throws Exception{
        List<EntityVo> list = getList();

        int index = 1;
//        for(EntityVo vo : list){
//            System.out.println(index+","+vo);
//        }

        Map<String,List<EntityVo>> map = new HashMap<>();

        for(EntityVo vo : list){
            String key = vo.getLongName();

            List<EntityVo> others = map.get(key);
            if(others==null){
                map.put(key,new ArrayList<>());
            }

            others = map.get(key);
            others.add(vo);

            map.put(key,others);
        }



        for(Map.Entry<String,List<EntityVo>> kv : map.entrySet()) {
            int max = kv.getValue().stream().map(EntityVo::getTime).max(Integer::compareTo).get().intValue();
            int min = kv.getValue().stream().map(EntityVo::getTime).min(Integer::compareTo).get().intValue();
            long count = kv.getValue().stream().map(EntityVo::getTime).count();
            double agerage = kv.getValue().stream().mapToDouble(EntityVo::getTime).average().getAsDouble();
            System.out.println(index+" 次数="+count+",key="+kv.getKey() +" max="+max+", min="+min+" 次数="+count+" 平均="+agerage);
            index = index+1;
        }


    }

    private static List<EntityVo> getList(){
        File file = new File("D:\\xhzlog","long.log");

        List<EntityVo> list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
            //按字节数组读取 bytes存储的是读取的数据
            String str;
            while ((str = br.readLine()) != null) {
                int index = str.indexOf("className");
//                System.out.println(str.substring(index,str.length()));

                String strs[] = str.substring(index,str.length()).split("[, ]");
//                System.out.println(Arrays.toString(strs));

                EntityVo vo = new EntityVo();
                for(String s : strs) {
                    String as[] = s.split("=");
                    if(as[0].equals("className")){
                        vo.setClassName(as[1]);
                    }
                    if(as[0].equals("methodName")){
                        vo.setMethodName(as[1]);
                    }
                    if(as[0].equals("spentTime")){
                        vo.setTime(Integer.parseInt(as[1].replace("ms","")));
                    }
                    vo.setLongName(vo.getClassName()+"."+vo.getMethodName());

                }
                if(!vo.getClassName().equals("RateLimiterFilter")) {
                    list.add(vo);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }

}
