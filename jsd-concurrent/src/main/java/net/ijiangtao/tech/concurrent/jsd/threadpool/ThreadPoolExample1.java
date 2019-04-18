package net.ijiangtao.tech.concurrent.jsd.threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample1 {

    // Task class to be executed (Step 1)
    static class Task implements Runnable {

        private String name;

        public Task(String s) {
            name = s;
        }

        // Prints task name and sleeps for 1s
        // This Whole process is repeated 2 times
        public void run() {
            try {
                for (int i = 0; i <= 1; i++) {
                    if (i == 0) {
                        //prints the initialization time for every task
                        printTimeMsg("Initialization");
                    } else {
                        // prints the execution time for every task
                        printTimeMsg("Executing");
                    }
                    Thread.sleep(1000);
                }
                System.out.println(name + " complete");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void printTimeMsg(String state) {
            Date d = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
            System.out.println(state+" Time for"+ " task name - " + name + " = " + ft.format(d));
        }

    }

    // Maximum number of threads in thread pool
    static final int MAX_T = 3;
    // creates a thread pool with MAX_T no. of
    // threads as the fixed pool size(Step 2)
    private static final ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

    public static void main(String[] args) {
        // creates five tasks
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5");

        // passes the Task objects to the pool to execute (Step 3)
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        // pool shutdown ( Step 4)

        Thread

        pool.shutdown();
    }


}
