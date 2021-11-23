package ex;

import java.util.concurrent.TimeUnit;

/**
 * ExMain
 *
 * @author kq
 * @date 2021-11-20 10:31
 * @since 1.0.0
 */
public class ExMain {

    AA aa = new AA();
    AA aaa = new AA();
    BB bb = new BB();
    BB bbb = new BB();
    CC cc = new CC();
    CC ccc = new CC();
    EmptyObj emptyObj = new EmptyObj();

    public static void main(String[] args) throws Exception{

        ExMain ex = new ExMain();

        ex.aa.toString();
        ex.bb.toString();
        ex.cc.toString();
        ex.aaa.toString();
        ex.bbb.toString();
        ex.ccc.toString();
        ex.emptyObj.toString();


        TimeUnit.MINUTES.sleep(600);
    }

}
