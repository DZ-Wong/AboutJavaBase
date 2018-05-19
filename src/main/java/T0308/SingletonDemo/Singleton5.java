package T0308.SingletonDemo;

/**
 * 静态内部类。
 * 由于加载一个类时，其内部类不会被加载。这样保证了只有调用getInstance()时才会产生实例，控制了生成实例的时间，实现了延迟加载。

    并且去掉了synchronized，让性能更优，用static来确保唯一性。
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
