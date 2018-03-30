package T0308.SingletonDemo;

/**
 * 懒汉，线程安全。
 * Created by vip on 2018/3/30.
 */
public class Singleton2 {
    private static Singleton2 instance;
    private Singleton2() {}
    public static synchronized Singleton2 getInstance() {
        if (null == instance) {
            instance = new Singleton2();
        }
        return instance;
    }
}
