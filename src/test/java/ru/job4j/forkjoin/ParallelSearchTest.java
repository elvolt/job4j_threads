package ru.job4j.forkjoin;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchTest {
    @Test
    public void whenFound() {
        String[] array = {"Pavel", "Petr", "Ivan", "Sergey", "Zinaida",
                "Olga", "Stepan", "John", "Alex", "Julia", "Konstantin"};
        assertEquals(4, ParallelSearch.search(array, "Zinaida"));
    }

    @Test
    public void whenNotFound() {
        String[] array = {"Pavel", "Petr", "Ivan", "Sergey", "Zinaida",
                "Olga", "Stepan", "John", "Alex", "Julia", "Konstantin"};
        assertEquals(-1, ParallelSearch.search(array, "Alena"));
    }
}