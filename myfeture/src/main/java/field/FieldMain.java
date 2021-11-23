package field;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * FieldMain
 *
 * @author kq
 * @date 2021-11-18 23:40
 * @since 1.0.0
 */
public class FieldMain {

    A a = new A();
    B b = new B();
    C c = new C();

    public static void main(String[] args) throws Exception{
        FieldMain f = new FieldMain();

        ClassLayout layout1 = ClassLayout.parseInstance(f.a);
        ClassLayout layout2 = ClassLayout.parseInstance(f.b);
        ClassLayout layout3 = ClassLayout.parseInstance(f.c);

        System.out.println(layout1);
        System.out.println(layout2);
        System.out.println(layout3);

        System.out.println(layout1.toPrintable());
        System.out.println(layout2.toPrintable());
        System.out.println(layout3.toPrintable());

        f.a.toString();
        f.b.toString();
        f.b.toString();

        TimeUnit.MINUTES.sleep(600);

    }

}
