package net.ijiangtao.tech.concurrent.jsd.guava;

public class GuavaExample {

    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup systenThreadGroup = mainThreadGroup.getParent();
        System.out.println("systenThreadGroup name = " + systenThreadGroup.getName());
        System.out.println("mainThreadGroup name = " + mainThreadGroup.getName());

        ThreadGroup subThreadGroup1 = new ThreadGroup("subThreadGroup1");
        ThreadGroup subThreadGroup2 = new ThreadGroup(subThreadGroup1, "subThreadGroup2");
        System.out.println("subThreadGroup1 parent name = " + subThreadGroup1.getParent().getName());
        System.out.println("subThreadGroup2 parent name = " + subThreadGroup2.getParent().getName());


        ThreadGroup tg1 = new ThreadGroup("Parent ThreadGroup");

        Thread t1 = new Thread(tg1, "one");
        t1.start();

        Thread t2 = new Thread(tg1, "two");
        t2.start();

        Thread t3 = new Thread(tg1, "three");
        t3.start();

        System.out.println("Thread Group Name: " + tg1.getName());

        tg1.list();

    }

}
