package com.kq.getclass;

/**
 * @author kq
 * @date 2021-11-18 14:56
 * @since 2020-0630
 */
public class GetClassDemo {

    public static void main(String[] args) {
        Cartoon x = new Cartoon();
        Drawing one = (Drawing) x;
        Art two = (Art) x;
        if (one == two) {
            System.out.println("oen == two ");
        } else {
            System.out.println("!=");
        }

        Class clazz = Drawing.class;



        System.out.println(clazz);
        System.out.println(Drawing.class);
        System.out.println(x.getClass());

        System.out.println(x.toString());
        System.out.println(one.toString());
        System.out.println(two.toString());
    }

}
