package net.ijiangtao.tech.concurrent.jsd.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试OOM
 * @author ijiangtao.net
 */
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

    public static void main(String[] args) {
        newCachedThreadPoolTesterBadly();
    }

}
