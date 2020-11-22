package com.backend.notificationengine.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    String type;
    String from;
    String to;
    int sendTime;
    int templateId;
    int id;
}
