package itable;

/**
 * ITableMain1
 *
 * @author kq
 * @date 2021-11-20 22:28
 * @since 1.0.0
 */
public class ITableMain1 {

    static AA a = new AA();

    public void mainPrivate(){

    }


    public static void main(String[] args) {
        ITableMain1 main1 = new ITableMain1();
        main1.mainPrivate();

        IName name = new AA();
        name.getName();

        AA aa = new AA();
        aa.getName();
        aa.finalFun();

        AA.staticFun();


        System.out.println(AA.PAGE);
        System.out.println(a);


    }

}
