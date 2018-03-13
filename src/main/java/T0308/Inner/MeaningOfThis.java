package T0308.Inner;

/**
 * Created by vip on 2018/3/13.
 */
public class MeaningOfThis {
    public final int value2 = 4;
    public void doIt() {
        int value3 = 6;
        Runnable r = new Runnable() {
            public final int value = 5;
            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);//5
                System.out.println(value);//10
                System.out.println(value2);//4
                System.out.println(value3);//6
            }
        };
        r.run();
    }

    public static void main(String[] args) {
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }
}
