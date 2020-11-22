package com.backend.notificationengine.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private static List<String> message = new ArrayList<>();

    public List<String> getLogs() {
        return message;
    }

    public void emptyList() {
        message.clear();
    }

    public void postDelayed(final long delayMillis, String data) {

        final long requested = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        long leftToSleep = requested + delayMillis - System.currentTimeMillis();
                        if (leftToSleep > 0) {
                            Thread.sleep(leftToSleep);
                        }
                        break;
                    } catch (InterruptedException ignored) { }
                    finally {
                        System.out.println(data);
                        message.add(data + " Time: " + java.time.LocalTime.now());
                    }
                }
            }
        }).run();
    }
}
