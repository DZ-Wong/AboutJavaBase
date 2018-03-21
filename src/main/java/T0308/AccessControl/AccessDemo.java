package T0308.AccessControl;

/**
 * Created by vip on 2018/3/21.
 */
public class AccessDemo extends Cookie {
    public AccessDemo() {
        System.out.println("AccessDemo Constructor");
    }

    public void chomp() {
        bite();//这时和Cookie还在同一个package下，能够调用，在其他包下时报错。
    }


    public static void main(String[] args) {
        AccessDemo demo = new AccessDemo();
        demo.chomp();
    }
}
