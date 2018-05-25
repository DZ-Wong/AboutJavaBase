package ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * Created by vip on 2018/5/23.
 */
public class ThreadShare {
    private static volatile int count = 0;

    public static void test() {
        Executor executors = Executors.newCachedThreadPool();
        for (int i = 1; i <= 100; i++) {
            int taskId = i;
            Runnable task = () -> {
                for (int j = 1; j <= 1000; j++) {
                    count++;
                }
                System.out.println(taskId + " : " + count);
            };
            executors.execute(task);
        }

//        ()-> {};
    }

    /*//安全并发的策略
    1.限制数据共享 ---不共享变量
    2.不变性 --- 共享不可修改的对象
    3.锁*/

    public static void main(String[] args) {
        test();
    }
}
