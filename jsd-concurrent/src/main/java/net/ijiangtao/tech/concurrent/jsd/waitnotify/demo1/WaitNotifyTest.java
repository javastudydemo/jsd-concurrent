package net.ijiangtao.tech.concurrent.jsd.waitnotify.demo1;

import java.util.concurrent.atomic.AtomicBoolean;

public class WaitNotifyTest {

    public static void main(String[] args) {
        //testPolling();
        testNotify();
        testNotifyAll();
    }

    public static void testPolling() {

        Message msg = new Message("process it");

        PollingWaiter waiter = new PollingWaiter(msg);

        new Thread(waiter, "PollingWaiter").start();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setIsAvailable(new AtomicBoolean(true));
        System.out.println("over");
    }

    public static void testNotify() {
        Message msg = new Message("process it");

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();

        Waiter waiter2 = new Waiter(msg);
        new Thread(waiter2, "waiter2").start();

        Notifier notifier = new Notifier(msg, false);
        new Thread(notifier, "notifier").start();

        System.out.println("All the threads are started");
    }

    public static void testNotifyAll() {
        Message msg = new Message("process it");

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();

        Waiter waiter2 = new Waiter(msg);
        new Thread(waiter2, "waiter2").start();

        Notifier notifier = new Notifier(msg, false);
        new Thread(notifier, "notifier").start();

        System.out.println("All the threads are started");
    }
}
