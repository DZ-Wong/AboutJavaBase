package T0306;

/**
 * 内部类。
 * Created by vip on 2018/3/6.
 */
public class InnerClassDemo {
//    public void innerClassDemo() {
//        Runnable r = new Runnable() {
//            public void run() {
//                System.out.println(this.getClass());
//            }
//        };
//    }

    static Runnable x = new Runnable() {

        public void run() {
            System.out.println(this.getClass());
        }
    };


    public static void main(String[] args) {
        x.run();
    }
}
