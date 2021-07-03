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

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T result = queue.poll();
        notifyAll();
        return result;
    }

    public synchronized int getSize() {
        return queue.size();
    }
}
