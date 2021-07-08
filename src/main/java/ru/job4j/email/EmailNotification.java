package ru.job4j.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String subjectFormat = "Notification %s to email %s";
        String  bodyFormat = "Add a new event to %s";
        pool.submit(() -> {
            String name = user.getUsername();
            String email = user.getEmail();
            String subject = String.format(subjectFormat, name, email);
            String body = String.format(bodyFormat, name);
            send(subject, body, email);
        });
    }

    public void send(String subject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
