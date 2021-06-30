package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private String filepath;

    public Wget(String url, int speed, String filepath) {
        this.url = url;
        this.speed = speed;
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filepath)) {
            byte[] dataBuffer = new byte[1024];
            long timeBefore = System.currentTimeMillis();
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long timeAfter = System.currentTimeMillis();
                long downloadingTimeSeconds = (timeAfter - timeBefore) / 1000;
                if (downloadingTimeSeconds < speed) {
                    long pauseTime = speed - downloadingTimeSeconds;
                    Thread.sleep(pauseTime * 1000);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Three arguments expected");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String filepath = args[2];
        Thread wget = new Thread(new Wget(url, speed, filepath));
        wget.start();
        wget.join();
    }
}
