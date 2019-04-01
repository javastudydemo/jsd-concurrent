package net.ijiangtao.tech.concurrent.jsd.threadpool;

import java.time.LocalTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ijiangtao.net
 */
public class ThreadPoolExample3 {

    private static final AtomicInteger threadNumber = new AtomicInteger(1);

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(2000);
                System.out.println(Thread.currentThread().getName() + "-" + LocalTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static class MyThreadFactory implements ThreadFactory {

        private final String namePrefix;

        public MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, namePrefix + "-" + threadNumber.getAndIncrement());
        }

    }

    private static final ExecutorService executorService = new ThreadPoolExecutor(
            10,
            20, 30, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(50),
            new MyThreadFactory("MyThreadFromPool"),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        // creates five tasks
        Task r1 = new Task();
        Task r2 = new Task();
        Task r3 = new Task();
        Task r4 = new Task();
        Task r5 = new Task();

        // submit方法有返回值
        Future future = executorService.submit(r1);
        System.out.println("r1 isDone ? " + future.isDone());

        // execute方法没有返回值
        executorService.execute(r2);
        executorService.execute(r3);
        executorService.execute(r4);
        executorService.execute(r5);

        //关闭线程池
        executorService.shutdown();

    }

}
