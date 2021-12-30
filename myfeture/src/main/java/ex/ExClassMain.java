package ex;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * ExClassMain
 *
 * @author kq
 * @date 2021-11-20 10:59
 * @since 1.0.0
 */
public class ExClassMain {

    AA aa = new AA();
    AA aaa = new AA();
    BB bb = new BB();
    BB bbb = new BB();
    CC cc = new CC();
    CC ccc = new CC();

    public static void main(String[] args) throws Exception{

        ClassLayout layout1 = ClassLayout.parseClass(AA.class);
        ClassLayout layout2 = ClassLayout.parseClass(BB.class);
        ClassLayout layout3 = ClassLayout.parseClass(CC.class);

//        System.out.println(layout1);
//        System.out.println(layout2);
//        System.out.println(layout3);

        System.out.println(layout1.toPrintable());
        System.out.println(layout2.toPrintable());
        System.out.println(layout3.toPrintable());



        TimeUnit.MINUTES.sleep(600);
    }

}
