package com.backend.notificationengine.controller;

import com.backend.notificationengine.objects.Notification;
import com.backend.notificationengine.objects.Template;
import com.backend.notificationengine.services.FileService;
import com.backend.notificationengine.services.NotificationService;
import com.backend.notificationengine.services.ObjectMapperService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    FileService fileService;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @CrossOrigin
    @PostMapping(value = "/initiatenotifications")
    public @ResponseBody String initiateNotifications(@RequestBody String data) {
        try {
            Notification notifications[] = ObjectMapperService.getObjectMapper().readValue(data, Notification[].class);
            executor.submit(() -> notificationService.sendNotification(notifications));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "STARTING";
    }

    @CrossOrigin
    @GetMapping(value = "/getalltemplates")
    public @ResponseBody List<Template> getAllTemplates() {
        return notificationService.getAllTemplates();
    }

    @CrossOrigin
    @GetMapping(value = "/getlogs")
    public @ResponseBody List<String> getAllLogs() {
        return fileService.getLogs();
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }
}
