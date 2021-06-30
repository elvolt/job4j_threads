package ru.job4j.stotage;

public interface Storage {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    boolean transfer(int fromIndex, int toIndex, int amount);
}
