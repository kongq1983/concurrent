package ex2;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @author kq
 * @date 2021-11-20 13:30
 * @since 2020-0630
 */
public class EX2Main {

    AA aa = new AA();
    BB bb = new BB();
    CC cc = new CC();
    DD dd = new DD();
    BBB bbb = new BBB();
    BBBB bbbb = new BBBB();
    Object object = new Object();

    public static void main(String[] args) throws Exception{


        EX2Main ex = new EX2Main();

        ex.bb.toString();
        ex.cc.toString();
        ex.dd.toString();
        ex.bbb.toString();
        ex.bbbb.toString();

        System.out.println( ClassLayout.parseInstance(ex.aa).toPrintable());
        System.out.println( ClassLayout.parseInstance(ex.object).toPrintable());

        TimeUnit.MINUTES.sleep(600);

    }

}
