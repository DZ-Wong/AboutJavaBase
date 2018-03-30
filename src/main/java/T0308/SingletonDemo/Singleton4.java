package T0308.SingletonDemo;

/**
 * 饿汉变种写法。
 * Created by vip on 2018/3/30.
 */
public class Singleton4 {
    private static Singleton4 instance = null;
    static {
        instance = new Singleton4();
    }
    private Singleton4() {}
    public static Singleton4 getInstance() {
        return instance;
    }
}
