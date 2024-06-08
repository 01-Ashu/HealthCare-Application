package com.Notification.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationDTO {
    private UUID id;
    private String type;
    private String message;



}