package ThreadPool;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 《Java核心技术》例子。
 */
public class ThreadDemoByBook {
    /**
     * 获取可用线程数
     */
    public static void test() {
        Runnable task = () -> {};//匿名函数写法
        Executor exec = Executors.newCachedThreadPool();

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors  " + processors);

    }

    /**
     * 线程池执行任务 ， 继承Runnable。
     */
    public static void test2() {
        Runnable hellos = () -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println("Hello "  + i);
            }
        };

        Runnable goodbyes = () -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println("Goodbye " + i);
            }
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(hellos);
        executor.execute(goodbyes);
    }

    /**
     * 常见使用
     */
    /*public static void test3() {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        Callable<V> task = ...;
        Future<V> result = exec.submit(task);//执行任务并获取结果。

        //为了能随时中断任务,需要周期性检查中断请求，
        Callable<V> task = () -> {
            while (true) {//判断条件
                if (Thread.currentThread().isInterrupted()) return null; //中断退出
                //下面是正常任务业务逻辑
            }
            return result;
        }

        //通过invokeAll，将Callable实例的一个集合传递给该方法,达到收集所有的任务结果
        //比方计算单词出现频率
        String word = "aaaaaaabadddddddabsdlkjljl";
        Set<Path> paths = ...;
        List<Callable<Long>> tasks = new ArrayList<>();
        for (Path p : paths) tasks.add(
                () -> {return  中word变量出现次数});

        List<Future<Long>> results = exec.invokeAll(tasks);
        long total = 0;
        for (Future<Long> result : results) total += result.get();

        //上一种主调任务会阻塞，直到所有的子任务完成。
        //ExecutorCompleetionService会以完成顺序返回future
        ExecutorCompletionService service =
                new ExecutorCompletionService(exec);
        for (Callable<V> task : tasks) service.submit(task);
        for (int i = 0; i < tasks.size(); i++) {
            //处理 services.take().get()
            //干点别的
        }

        //invokeAny 只要提交的任务中有一个完成并且无异常，就会返回future，取消其他任务。   ·
        //在发现一个匹配就结束的搜索是有用的。
        String words = ;
        Set<Path> files = ;
        List<Callable<Path>> tasks = new ArrayList<>();
        for (Path p : files) tasks.add(
                () -> {if (words 在p中出现) return p; else throw ...}
        );
        Path found = exec.invokeAny(tasks);
    }*/

    /**
     * 可能出错点分析
     * 可见性 -- 缓存与指令重排序
     * 默认情况下，优化机制假设不存在并发内存访问，需要变量可见时，有4种方式通知：
     * 1.定义变量final
     * 2.static的初始值在静态初始化后是可见的
     * 3.对volatile变量的改变是可见的
     * 4.发生在锁被释放前的改变对任何试图获取同一个锁的人可见
     */
//    private static boolean done = false;
    //确保该变量对所有线程可见，加volatile
    private static volatile boolean done = false;
    public static void visibility() {
        Runnable hellos = () -> {
            for (int i = 1; i <= 1000; i++) {
                System.out.println("Hello "  + i);
            }
            done = true;
        };

        Runnable goodbyes = () -> {
            int i = 1;
            while (!done) i++;//这里不生效,其他任务的done值对于此处不可见
//            if (!done) while(true) i++;//指令被重排序为这个
            System.out.println("Goodbye " + i);
        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(hellos);
        executor.execute(goodbyes);

    }

    /**
     * 竞争条件 -- ++
     */
    private static volatile int count = 0;
    public static void competitiveCondition() {

        count ++; //task 1 count = count + 1;
        //register1 = count + 1;
        //


        //
        count++; //task 2 count = count + 1;




    }

    public static void main(String[] args) {
        test();
        test2();
        visibility();
    }

}
