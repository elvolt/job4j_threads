package ru.job4j;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrementThenGet() throws InterruptedException {
        CASCount counter = new CASCount();
        Thread thread1 = new Thread(() ->
            IntStream.rangeClosed(1, 5).forEach(i -> counter.increment())
        );
        Thread thread2 = new Thread(() ->
            IntStream.rangeClosed(1, 7).forEach(i -> counter.increment())
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertEquals(12, counter.get());
    }
}