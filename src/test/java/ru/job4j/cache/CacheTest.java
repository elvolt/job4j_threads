package ru.job4j.cache;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        assertEquals(List.of(base1, base2), List.of(cache.get(1), cache.get(2)));
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        cache.delete(base1);
        assertEquals(base2, cache.get(2));
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        cache.add(base1);
        cache.add(base2);
        cache.update(base1);
        assertEquals(new Base(1, 1), cache.get(1));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateWithException() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 0);
        Base base2 = new Base(2, 0);
        Base base1Updated = new Base(1, 1);
        cache.add(base1);
        cache.add(base2);
        cache.update(base1Updated);
    }
}