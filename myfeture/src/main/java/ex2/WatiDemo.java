package ex2;

/**
 * @author kq
 * @date 2021-11-23 8:26
 * @since 2020-0630
 */
public class WatiDemo {

    private String name;
    int age = 0;

    public WatiDemo(String name){
        this.name = name;
    }

    public WatiDemo(int age){
        this.age = age;
    }

    public void test(){
        synchronized (this) {
            try {
                this.wait();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
