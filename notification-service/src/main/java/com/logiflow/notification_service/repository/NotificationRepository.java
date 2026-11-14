
package com.logiflow.notification_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.logiflow.notification_service.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    List<Notification> findByCustomerId(String customerId);

    List<Notification> findByCustomerIdAndReadFalse(String customerId);

    List<Notification> findByOrderId(String orderId);
}
