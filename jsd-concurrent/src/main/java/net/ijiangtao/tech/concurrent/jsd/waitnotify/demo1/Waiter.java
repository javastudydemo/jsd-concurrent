package net.ijiangtao.tech.concurrent.jsd.waitnotify.demo1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Waiter implements Runnable{

    private Message msg;

    public Waiter(Message m){
        this.msg=m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try{
                System.out.println(name+" : waiting to get notified at time:"+ LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name+" : waiter thread got notified at time:"+LocalDateTime.now().format(DateTimeFormatter.ISO_TIME));
            //process the message now
            System.out.println(name+" : processed: "+msg.getMsg());
        }
    }

}
