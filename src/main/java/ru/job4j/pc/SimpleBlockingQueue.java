package ru.job4j.pc;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private final int size;

    @GuardedBy("this")
    private final Queue<T> queue;

    public SimpleBlockingQueue(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public synchronized void offer(T value) {
        while (queue.size() == size) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }

    public synchronized int getSize() {
        return queue.size();
    }
}
