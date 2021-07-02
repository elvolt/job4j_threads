package ru.job4j.stotage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage implements Storage {
    @GuardedBy("this")
    private final Map<Integer, User> storage = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        return storage.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    public synchronized boolean update(User user) {
        return storage.replace(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean delete(User user) {
        return storage.remove(user.getId(), user);
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        User to = storage.get(toId);
        if (from == null || to == null || from.getAmount() < amount) {
            return false;
        }
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        return true;
    }

    public static void main(String[] args) {
        Storage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);
        storage.transfer(1, 2, 50);
        System.out.println(user1.getAmount());
        System.out.println(user2.getAmount());
    }
}
