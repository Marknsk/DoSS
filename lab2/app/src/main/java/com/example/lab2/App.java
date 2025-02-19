package com.example.lab2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    private static final int NUMBER_OF_THREADS = 8;
    private static final int CALCULATION_LENGTH = 22;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            final int threadNumber = i + 1;
            executor.submit(() -> {
                long threadId = Thread.currentThread().getId();
                long startTime = System.currentTimeMillis();
                StringBuilder progressBar = new StringBuilder();
                for (int j = 0; j < CALCULATION_LENGTH; j++) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    progressBar.append("=");
                    System.out.printf("Thread number: %d, ID: %d: [%s%s] %d%%\n", threadNumber, threadId, progressBar, " ".repeat(CALCULATION_LENGTH - j - 1), (j + 1) * 100 / CALCULATION_LENGTH);
                }
                long endTime = System.currentTimeMillis();
                long runTime = endTime - startTime;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.printf("Thread number: %d, ID: %d finished. Run time: %d ms\n", threadNumber, threadId, runTime);
            });
        }
        executor.shutdown();
    }
}
