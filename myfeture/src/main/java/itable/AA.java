package itable;

/**
 * AA
 *
 * @author kq
 * @date 2021-11-20 22:28
 * @since 1.0.0
 */
public class AA implements IName{

    public static final int PAGE = 20;


    @Override
    public String getName() {
        return "AA";
    }

    public static void staticFun(){
        System.out.println();
    }

    public final void finalFun(){
        System.out.println();
    }

    private void privateFun(){
        System.out.println();
    }

}
