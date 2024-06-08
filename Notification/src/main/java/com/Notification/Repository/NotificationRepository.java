package com.Notification.Repository;

import com.Notification.Entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<Notification, UUID> {
}
