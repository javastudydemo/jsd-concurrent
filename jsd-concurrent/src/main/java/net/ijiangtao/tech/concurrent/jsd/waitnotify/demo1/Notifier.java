package net.ijiangtao.tech.concurrent.jsd.waitnotify.demo1;

public class Notifier implements Runnable {

    private boolean isAll = true;

    private Message msg;

    public Notifier(Message msg, boolean isAll) {
        this.msg = msg;
        this.isAll = isAll;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");
        try {

            Thread.sleep(3000);

            synchronized (msg) {

                System.out.println(name + " : got the msg : "+msg.getMsg());

                msg.setMsg(name + " : Notifier work done");

                if (isAll) {
                    msg.notifyAll();
                } else {
                    msg.notify();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}