package T0308.SingletonDemo;

/**
 * 双重校验锁。
 * Created by vip on 2018/3/30.
 */
public class Singleton7 {
    private volatile static Singleton7 singleton7;
    private Singleton7() {}
    public static Singleton7 getInstance() {
        if (null == singleton7) {
            synchronized (Singleton7.class) {
                if (null == singleton7) {
                    singleton7 = new Singleton7();
                }
            }
        }

    return singleton7;

    }

}
