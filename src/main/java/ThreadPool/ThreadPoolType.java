package ThreadPool;

/**
 * Created by vip on 2018/4/9.
 */
public enum ThreadPoolType {
    CACHED,//newCachedThreadPool,可缓存，when池长度超过处理需要，则回收空闲线程
    FIXED,//newFixedThreadPool固定数目的，可重用的
    SCHEDULED,//newScheduledThreadPool定长的，支持定时及周期性任务
    SINGLE,//newSingleThreadExecutor单线程化，用唯一工作线程工作，保证任务按指定顺序执行，FIFO，LIFO，优先级
    SINGLESCHEDULED,//newSingleThreadScheduledExcutor单例，定期或延时执行任务
    WORKSTEALING,// newWorkStealing有足够线程的，支持并行级别，通过多个队列，减少竞争。
    FORKJOINPOOL//ForkJoinPool ，支持大任务分为小任务
}
