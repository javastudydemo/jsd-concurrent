package net.ijiangtao.tech.concurrent.jsd.waitnotify.demo1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PollingWaiter implements Runnable {
    private Message msg;
    public PollingWaiter(Message m) {
        this.msg = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            int count = 0;
            System.out.println(name + " : waiter starting at time: " + LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
            while (!msg.getIsAvailable().get()) {
                count++;
            }
            System.out.println(name + " : msg is available at time: " + LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
            System.out.println(name + " : msg is available after count: " + count);
            System.out.println(name + " : processed: " + msg.getMsg());
        }
    }
}
