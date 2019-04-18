package net.ijiangtao.tech.concurrent.jsd.threadgroup;

public class ThreadGroupExampleInterrupt {

    public static void main(String[] args) {

        Thread.currentThread().stop();
        Thread.currentThread().suspend();
        Thread.currentThread().resume();

        // Start two threads
        MyThread mt = new MyThread();
        mt.setName("A");
        mt.start();
        mt = new MyThread();
        mt.setName("B");
        mt.start();

        // Wait 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt all methods in the same thread group as the main thread
        Thread.currentThread().getThreadGroup().interrupt();

    }


    //一个启动以后进入等待，直到被interrupt的线程
    static class MyThread extends Thread {
        public void run() {
            synchronized ("A") {
                System.out.println(getName() + " about to wait.");
                try {
                    "A".wait();
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted.");
                }
                System.out.println(getName() + " terminating.");
            }
        }
    }


}
