package T0308.MultiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁demo。
 * Created by vip on 2018/5/28.
 */
public class LockDemo {
    //构造一个ReentrantReadWriteLock对象
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    //抽取读锁和写锁
    private Lock readLock = rwl.readLock();//可以被多个读操作共用
    private Lock writeLock = rwl.writeLock();//

    //对所有的获取方法加读锁
    public double getTotalBalance() {
        readLock.lock();
        try {
            //获取数据
            return 1.0D;
        } finally {
          readLock.unlock();
        }
    }

    //对所有写方法加写锁
    public void transfer(Double newAmt) {
        writeLock.lock();
        try {
            //写数据

        } finally {
            writeLock.unlock();
        }
    }
}
