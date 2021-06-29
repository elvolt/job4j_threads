package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] process = new String[] {"-", "\\", "|", "/"};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.print("\r load: " + process[i]);
                i = (i == process.length - 1 ? 0 : i + 1);
            } catch (InterruptedException e) {
                System.out.println("\r Loading completed");
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }
}
