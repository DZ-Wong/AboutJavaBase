package T0308.SingletonDemo;

/**
 * 静态内部类。
 * Created by vip on 2018/3/30.
 */
public class Singleton5 {
    private static class SingletonHolder {
        private static final Singleton5 INSTANCE = new Singleton5();
    }
    private Singleton5() {}
    public static final Singleton5 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
