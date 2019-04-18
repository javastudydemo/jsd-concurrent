package net.ijiangtao.tech.concurrent.jsd.future.demo1;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * FutureTask
 *
 * @author ijiangtao
 * @create 2019-04-15 20:53
 **/
public class FutureTaskDemo {


    private static ExecutorService executorService = new ThreadPoolExecutor(
            16,
            32,
            10L,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<Runnable>(
                    2048),
            new ThreadFactoryBuilder().setNameFormat("BatchSyncFullInventory-Pool-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    private static class DemoTask implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "";
        }
    }
    public static void test() {

        DemoTask task = new DemoTask();// 新建异步任务
        FutureTask<String> future = new FutureTask<String>(task) {
            // 异步任务执行完成，回调
            @Override
            protected void done() {
                try {
                    System.out.println("future.done():" + get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        // 创建线程池（使用了预定义的配置）
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(future);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        // 可以取消异步任务
        // future.cancel(true);

        try {
            // 阻塞，等待异步任务执行完毕-获取异步任务的返回值
            System.out.println("future.get():" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {

    }

}
