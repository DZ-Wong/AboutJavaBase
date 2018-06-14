package T0308.MultiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 并发创建方式。
 * Created by vip on 2018/5/25.
 */
public class CreateMode {

    /**
     * 继承Thread方式
     */
    public void m1Thread() {
        Thread thread = new MyThread();
        thread.start();

    }
    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("create new thread by extends Thread");
        }
    }

    /**
     * 实现Runnable方式
     */
    public void m2Runnable() {
        Thread thread2 = new Thread(new MyTask("继承Runnable"));
        Thread thread4 = new Thread(new MyTask("继承Runnable ")) {
            @Override
            public void run() {
                System.out.println(" 又重写了Thread里面的run方法");
            }
        };
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("create new thread by implements Runnable && inner class");
            }
        });
        Thread thread1 = new Thread(() -> {
            System.out.println("create new thread by implements Runnable && lambda ");
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    class MyTask implements Runnable {

        private String taskName;

        public MyTask(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println("create new thread by implements Runnable" + taskName);
        }
    }


    /**
     * FutureTask 方式
     */
    public void mFutureTask() {
        //类似 结果集
        FutureTask<Double> task =
            new FutureTask<Double>(new MyCallable());

        Thread thread = new Thread(task);
        thread.start();
        //主线程干点别的

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //
            System.out.println("sleep 被中断");
            e.printStackTrace();
        }

        System.out.println("main thread wait the result : ...");

        //需要获取结果时，阻塞获取结果
        try {
            Double d = task.get();
            System.out.println("result is : " + d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //用同一个FutureTask 再起一个线程
        Thread thread1 = new Thread(task);
        thread1.start();

    }
    class MyCallable implements Callable<Double> {
        @Override
        public Double call() throws Exception {
            return null;
        }
    }


    /**
     * 使用线程池ExecutorService、Executors
     */
    public void mExecutors() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new MyTask("线程池 完成任务1"));
        executorService.execute(new MyTask("线程池 完成任务2"));
        executorService.execute(new MyTask("线程池 完成任务3"));
    }



    public static void main(String[] args) {
        CreateMode createMode = new CreateMode();
        createMode.m2Runnable();
    }
}
