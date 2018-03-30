package T0308.SingletonDemo;

/**
 * 单例第一种写法，懒汉模式，线程不安全。
 * Created by vip on 2018/3/30.
 */
public class Singleton01 {
    private static Singleton01 instance;
    private Singleton01() {}
    public static Singleton01 getInstance() {
        if (null == instance) {
            instance = new Singleton01();
        }
        return instance;
    }
}
