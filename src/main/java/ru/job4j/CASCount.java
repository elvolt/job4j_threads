package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer currentValue;
        Integer newValue;
        do {
            currentValue = count.get();
            newValue = currentValue + 1;
        } while (!count.compareAndSet(currentValue, newValue));
    }

    public int get() {
        return count.get();
    }
}