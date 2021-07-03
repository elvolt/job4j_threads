package ru.job4j.pc;

public class Producer implements Runnable {
    private final SimpleBlockingQueue<Integer> queue;
    private final int count;

    public Producer(SimpleBlockingQueue<Integer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                queue.offer(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
