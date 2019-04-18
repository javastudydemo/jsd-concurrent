package net.ijiangtao.tech.concurrent.jsd.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    private static int count = 1;

    public static void main(String[] args) {


        Lock lock = new ReentrantLock();


        Runnable task = new Runnable() {
            @Override
            public void run() {


            }
        };

        lock.lock();
        try {
            count = count + 1;
        }finally {
            lock.unlock();
        }

        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);
        pool.execute(task);

        System.out.println(count);

        pool.shutdown();
    }


}
