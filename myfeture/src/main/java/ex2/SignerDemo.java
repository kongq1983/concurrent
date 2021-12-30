package ex2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author kq
 * @date 2021-11-20 15:45
 * @since 2020-0630
 */
public class SignerDemo {

    public int add(Integer a,Integer b) {
        return a+b;
    }

    public static void main(String[] args) throws Exception{

        Method method = SignerDemo.class.getMethod("add",Integer.class,Integer.class);

       String name = method.getName();
       Type type =  method.getGenericReturnType();
        System.out.println(name);
        System.out.println(type);

//       String signer = method.gets

//        Thread
//

    }

}
