package T0308.SingletonDemo;

/**
 * 饿汉模式，一开始就初始化好了。
 * Created by vip on 2018/3/30.
 */
public class Singleton3 {
    private static Singleton3 instance = new Singleton3();
    private Singleton3() {}
    public static Singleton3 getInstance() {
        return instance;
    }
}
