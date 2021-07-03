package ru.job4j.pc;

public class Consumer implements Runnable {
    private final SimpleBlockingQueue<Integer> queue;
    private final int count;

    public Consumer(SimpleBlockingQueue<Integer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            queue.poll();
        }
    }
}
