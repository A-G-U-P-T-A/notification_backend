package com.backend.notificationengine.services;

import com.backend.notificationengine.objects.Notification;
import com.backend.notificationengine.objects.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    FileService fileService;

    @Autowired
    ConfigLoaderService configLoaderService;

    public void sendNotification(Notification[] notifications) {
        for (int i=0;i<notifications.length;i++) {
            Notification notification = notifications[i];
            String template = configLoaderService.getTemplateFromId(notification.getTemplateId());
            template = template.replaceAll("<customer.name>", notification.getTo()).replaceAll("<employee.name>", notification.getFrom());
            template = template + " type: " + notification.getType();
            fileService.postDelayed(notification.getSendTime()*60000, template);
        }
    }

    public List<Template> getAllTemplates() {
        return configLoaderService.getTemplates();
    }
}
