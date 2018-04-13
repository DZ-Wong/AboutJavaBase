package ThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by vip on 2018/4/9.
 */
public class Demo {

    /**
     * 根据任务来临的需要决定是否创建新的线程，也就是如果来了新任务又没有空闲线程，它就会新建一个线程
     * @throws Exception
     */
    public static void newCachedThreadPoolDemo() throws Exception{
        ExecutorService m = Executors.newCachedThreadPool();
        for (int i = 1; i < 10; i++) {
            final int count = i;
            m.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread() +
                    "负责了"+ count + "次任务");
                }
            });
            //控制for循环的阻塞
//            Thread.sleep(1);/*这句话制造阻塞，影响是否新建线程*/
            //注释掉后 用了10个线程
            //未注释时，复用老线程只用了1 2个线程
        }
    }

    /**
     * 一个固定大小的、可重用是线程池.
     * @throws Exception
     */
    public static void newFixedThreadPoolDemo() throws Exception{
        ExecutorService m = Executors.newFixedThreadPool(4);
        for (int i = 1; i < 10; i++) {
            final int count = i;
            m.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程：" + Thread.currentThread() +
                                       "负责了"+ count + "次任务");
                }
            });
            Thread.sleep(1000);/*这句话制造阻塞，影响是否新建线程*/

        }
    }

    /**
     * 一个定长线程池，支持定时及周期性任务执行
     */
    public static void newScheduledThreadPoolDemo() {
        ScheduledExecutorService m = Executors.newScheduledThreadPool(4);
        m.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Date now = new Date();
                System.out.println("线程"+ Thread.currentThread() + "报时：" +
                now);
            }
        }, 1, 2, TimeUnit.SECONDS);//延迟1S执行，每隔2S执行一次
    }


    public static void newWorkStealingPoolDemo() {
        //设置并行级别为2，即默认每时每刻只有两个线程同时执行
        ExecutorService m = Executors.newWorkStealingPool(2);

        for (int i = 1; i <= 10 ; i++) {
            final int count = i;
            m.submit(new Runnable() {
                         @Override
                         public void run() {
                             Date now = new Date();
                             System.out.println("线程"+ Thread.currentThread() + "完成任务：" +
                             count + "     时间为： " + now.getTime());

                             try {
                                 Thread.sleep(1000);
                             } catch ( InterruptedException e) {
                                e.printStackTrace();
                             }
                         }
                     }

            );
        }
    }

    public static void main(String[] args) {
        try {
            newCachedThreadPoolDemo();

            newScheduledThreadPoolDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        newWorkStealingPoolDemo();
        //主线程陷入死循环，来观察结果，否则是看不到结果的
        while (true) {

        }
    }

}
