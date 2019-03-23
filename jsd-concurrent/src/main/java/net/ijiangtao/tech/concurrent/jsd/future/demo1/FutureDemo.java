package net.ijiangtao.tech.concurrent.jsd.future.demo1;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {

    private static ExecutorService executor = Executors.newSingleThreadExecutor();


    public static Future<Integer> calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(3000);
            return input * input;
        });

    }

    public static void test1() {
        Future<Integer> calculateFuture = calculate(100);

        System.out.println(calculateFuture.isDone());

        System.out.println(calculateFuture.isCancelled());
        System.out.println(calculateFuture.cancel(false));
        System.out.println(calculateFuture.isCancelled());

        //System.out.println(calculateFuture.get(2, TimeUnit.SECONDS));
        //System.out.println(calculateFuture.get());

        //System.out.println(calculateFuture.isDone());
        executor.shutdown();
    }

    public static void test2() throws Exception {
        MyRunnable myrunnableobject1 = new MyRunnable(1000);
        MyRunnable myrunnableobject2 = new MyRunnable(2000);

        FutureTask<String> futureTask1 = new FutureTask<>(() -> {
            System.out.println("future task");
        },
                "FutureTask1 is complete");

        futureTask1.run();


        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {

            return 8 * 8;
        });
        futureTask2.run();
        System.out.println(futureTask2.get());

    }


    public static String downloadContents(URL url) throws IOException {
        try (InputStream input = url.openStream()) {
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        }
    }

    public static void test3() {

        try {
            final String contents = downloadContents(new URL("http://www.ijiangtao.net"));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        //Future<Integer> calculateFuture = calculate(100);

        //System.out.println("calculate result: "+calculateFuture.get());

        //System.out.println(calculateFuture.get(2, TimeUnit.SECONDS));

        //System.out.println(calculateFuture.isDone());
        //while (!calculateFuture.isDone()){ }
        //System.out.println(calculateFuture.isDone());

        //System.out.println("isCancelled = "+calculateFuture.isCancelled());
        //Thread.sleep(3000);
        //System.out.println("cancel success = "+calculateFuture.cancel(true));
        //System.out.println("isCancelled = "+calculateFuture.isCancelled());

        //System.out.println(calculateFuture.get(2, TimeUnit.SECONDS));
        //System.out.println(calculateFuture.get());
        //executor.shutdown();

        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() throws Exception {

                long start = System.currentTimeMillis();
                Thread.sleep(100);
                long end = System.currentTimeMillis();

                long seed = end - start;
                System.out.println("seed=" + seed);

                return seed;
            }
        };

        List<Callable<Long>> tasks = new ArrayList<>();
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);
        tasks.add(callable);

        int poolSize = Runtime.getRuntime().availableProcessors();
        System.out.println("poolSize=" + poolSize);
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        List<Future<Long>> futures = executorService.invokeAll(tasks);

        long result = 0;
        for (Future<Long> future : futures) {
            result += future.get();
        }
        System.out.println("result=" + result);

        executorService.shutdown();

    }

    static class MyRunnable implements Runnable {

        private final long waitTime;

        public MyRunnable(int timeInMillis) {
            this.waitTime = timeInMillis;
        }

        @Override
        public void run() {
            try {
                // sleep for user given millisecond
                // before checking again
                Thread.sleep(waitTime);

                // return current thread name
                System.out.println("************" + Thread
                        .currentThread()
                        .getName());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
