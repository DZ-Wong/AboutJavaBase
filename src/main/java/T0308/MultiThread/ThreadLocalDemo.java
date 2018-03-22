package T0308.MultiThread;

import java.util.Random;

/**
 * Created by vip on 2018/3/22.
 */
public class ThreadLocalDemo implements Runnable{
    /*线程局部变量ThreadLocal
    * 用一个map存储每个线程的变量的副本 --  空间换时间 在spring 中常用
    * synchronized 同步机制（利用锁） 每个变量唯一--时间换空间*/

    /*
    *Synchronized实现内存共享，ThreadLocal为每个线程维护一个本地变量。
采用空间换时间，它用于线程间的数据隔离，为每一个使用该变量的线程提供一个副本，每个线程都可以独立地改变自己的副本，而不会和其他线程的副本冲突。
ThreadLocal类中维护一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值为对应线程的变量副本。
ThreadLocal在Spring中发挥着巨大的作用，在管理Request作用域中的Bean、事务管理、任务调度、AOP等模块都出现了它的身影。
Spring中绝大部分Bean都可以声明成Singleton作用域，采用ThreadLocal进行封装，因此有状态的Bean就能够以singleton的方式在多线程中正常工作了。
*/

    //ThreadLocal
    //创建线程局部变量studentlocal，用来保存student对象
    private final static ThreadLocal studentLocal = new ThreadLocal();

    /**
     * 业务方法。
     */
    public void accessStudent() {
        //获取当前现场的名字
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running");
        //打印一个随机数
        Random random = new Random();
        int age = random.nextInt();
        System.out.println("Thread " + currentThreadName + " set age to :" + age);

        //获取student对象，并将随机数插入对象属性
        Student student = getStudent();
        student.setAge(age);
        System.out.println("Thread " + currentThreadName + " first age is :" + age);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + currentThreadName + " second read age is :" +age);
    }

    /**
     * 获取当前线程的Student对象。
     */
    protected Student getStudent() {
        //获取本地线程变量并强制转换为Student类型
        Student student = (Student) studentLocal.get();
        //线程首次执行此方法的时候，get方法肯定为null
        if (student == null) {
            //首次初始化
            student = new Student();
            studentLocal.set(student);
        }
        return student;
    }

    @Override
    public void run() {
        accessStudent();
    }

    public static void main(String[] args) {
        ThreadLocalDemo td = new ThreadLocalDemo();
        Thread t1 = new Thread(td, "a");
        Thread t2 = new Thread(td, "b");
        t1.start();
        t2.start();
    }
}


class Student {
    private int age = 0;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}