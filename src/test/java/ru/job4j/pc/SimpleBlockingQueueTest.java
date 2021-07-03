package ru.job4j.pc;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOfferAndPollThenGetSize() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(new Producer(queue, 4));
        Thread consumer = new Thread(new Consumer(queue, 2));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(2, queue.getSize());
    }
}