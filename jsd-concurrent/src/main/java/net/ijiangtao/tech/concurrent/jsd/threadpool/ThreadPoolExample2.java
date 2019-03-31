package net.ijiangtao.tech.concurrent.jsd.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExample2 {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void newCachedThreadPoolTesterBadly() {
        System.out.println("begin............");
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            executorService.execute(new Task());
        }
        System.out.println("end.");
    }

    private static class MyThreadFactory implements ThreadFactory {

        private final AtomicInteger threadNumber = new AtomicInteger(1);

        private final String namePrefix;

        public MyThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(namePrefix + "-" + threadNumber.get());
        }

    }

    //newCachedThreadPoolTesterBadly();

    private static final ExecutorService executorService2 = new ThreadPoolExecutor(
            10,
            100,30, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            new MyThreadFactory(""),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

    }

}
