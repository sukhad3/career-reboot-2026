package com.sukhdev.reboot.careerreboot.labs.day1;

import java.util.concurrent.Executors;

public class VirtualThreadDemo {

    public static void main(String[] args) throws Exception {

        try (var executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= 10; i++) {

                int task = i;

                executor.submit(() -> {

                    System.out.println(
                            "Task "
                                    + task
                                    + " running on "
                                    + Thread.currentThread());

                    Thread.sleep(1000);

                    return null;

                });

            }

        }

    }

}