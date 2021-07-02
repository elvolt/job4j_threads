package ru.job4j;

import net.jcip.annotations.ThreadSafe;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count < total) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountBarrier barrier = new CountBarrier(3);
        Thread thread1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " finished");
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " finished");
                }
        );
        Thread thread3 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    barrier.count();
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " finished");
                }
        );
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        System.out.println(thread1.getName()
                + " " + thread1.getState());
        System.out.println(thread2.getName()
                + " " + thread2.getState());
        Thread.sleep(1000);
        thread3.start();
        Thread.sleep(1000);
    }
}
