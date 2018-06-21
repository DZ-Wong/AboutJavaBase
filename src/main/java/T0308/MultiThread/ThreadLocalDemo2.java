package T0308.MultiThread;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 局部变量。
 * Created by vip on 2018/5/28.
 */
public class ThreadLocalDemo2 {

    //https://blog.csdn.net/Tian779278804/article/details/76059923
    //ThreadLocal用于数据隔离，即当很多线程需要多次使用同一个对象，
    // 并且需要该对象具有相同初始化值的时候最适合使用ThreadLocal。
    private static ThreadLocal<DateFormat> threadLocal =
        new ThreadLocal<DateFormat>() {
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }
        };

    public static Date parse(String dateStr) throws ParseException{
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }


//    @Test
    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
       //计数器，可以在任意位置设置计数减一,确保await之前的操作能执行完。
        final CountDownLatch cdOrder = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() +
                        ":" + ThreadLocalDemo2.parse("2017-06-24 06:02:20"));
                        cdOrder.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            //将新创建的任务添加到线程池
            service.execute(runnable);
        }
    }
}
