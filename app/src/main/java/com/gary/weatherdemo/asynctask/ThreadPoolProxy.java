package com.gary.weatherdemo.asynctask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 线程池代理类
 * GoF23 设计模式 4：代理模式：新增一个代理类替封装对原对象的访问
 */
public class ThreadPoolProxy {
    /**
     * para1:corePoolSize核心线程池大小
     * 如果设置allowCoreThreadTimeOut为false的情况下：
     * 即使当线程池中的线程处于空闲状态，这些线程也不会被线程池中移除。
     * 如果设置了allowCoreThreadTimeOut为true,
     * 那么当核心线程在空闲了一段时间后依旧没有用于工作，那么将会从线程池中移除。
     * 注意:(allowCoreThreadTimeOut默认为false，通常情况下也无需做修改)
     */
    private final int mCorePoolSize;

    /**
     * para2:maximumPoolSize:线程池中所允许创建最大线程数量
     */
    private final int mMaximumPoolSize;

    /**
     * para3:keepAliveTime:当线程池中的线程数量大于核心线程数，
     * 如果这些多出的线程在经过了keepAliveTime时间后，
     * 依然处于空闲状态，那么这些多出的空闲线程将会被结束其生命周期。
     */
    private final long mKeepAliveTime;

    /**
     * para4:unit:keepAliveTime的时间单位
     */
    private final TimeUnit unit = TimeUnit.MILLISECONDS;

    /**
     * para5:workQueue线程池的缓存队列（阻塞队列）
     * 提交的任务将被存储在workQueue进行缓冲。
     * 该队列只能存放通过execute方法提交的Runnable任务。
     * <p>
     * type1:ArrayBlockingQueue:  FIFO,一个由数组结构组成的有界阻塞队列。
     * type2:LinkedBlockingQueue:  FIFO, 一个由链表结构组成的有界阻塞队列。
     * type3:PriorityBlockingQueue:  FIFO,一个支持优先级排序的无界阻塞队列。
     * <p>
     * ？DealyQueue：一个使用优先级队列实现的无界阻塞队列。
     * ?SynchronousQueue：一个不存储元素的阻塞队列。
     * ?LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
     * ?LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
     */
    private final BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(4);

    /**
     * para6:threadFactory:线程池中用于创建线程的工厂
     * 在这里使用线程工厂的目的也是为了解耦,将创建的实现细节通过工厂进行封装，
     * 而不是直接将创建的方式固化在ThreadPoolExecutor本身的代码中。
     * Thread newThread(Runnable r)
     */
    private final ThreadFactory threadFactory = Executors.defaultThreadFactory();//线程工厂

    /**
     * para7:RejectedExecutionHandler:线程池对拒绝任务的处理策略.
     * 当线程池中的线程数量达到最大并且阻塞队列也已经满了无法再添加任务时，线程池所采取的处理策略。
     * <p>
     * type1:ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * type2:ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * type3:ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * type4:ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     */
    private final RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

    private ThreadPoolExecutor mPool;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.mCorePoolSize = corePoolSize;
        this.mMaximumPoolSize = maximumPoolSize;
        this.mKeepAliveTime = keepAliveTime;
    }

    private void initPool() {
        if (mPool == null || mPool.isShutdown()) {
            mPool = new ThreadPoolExecutor(
                    mCorePoolSize,//para1:核心线程池大小
                    mMaximumPoolSize,//para2:最大线程池大小
                    mKeepAliveTime,//para3:保持存活的时间
                    unit,//para4:保持存活時間单位
                    workQueue,//para5:线程池的缓存队列
                    threadFactory,//para6:线程工厂
                    handler);//para7:异常捕获器
        }
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initPool();
        mPool.execute(task);
    }

    /**
     * 提交任务?
     */
    public Future<?> submit(Runnable task) {
        initPool();
        return mPool.submit(task);
    }

    /**
     * 取消任务
     */
    public void remove(Runnable task) {
        if (mPool != null && !mPool.isShutdown()) {
            mPool.getQueue().remove(task);
        }
    }
}