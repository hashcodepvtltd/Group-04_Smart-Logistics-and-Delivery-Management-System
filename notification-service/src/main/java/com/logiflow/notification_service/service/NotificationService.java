
package com.logiflow.notification_service.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.logiflow.notification_service.model.Notification;
import com.logiflow.notification_service.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    // Create a new notification
    public Notification createNotification(Notification notification) {
        LocalDateTime now = LocalDateTime.now();

        notification.setRead(false);
        notification.setCreatedAt(now);
        notification.setUpdatedAt(now);

        return notificationRepository.save(notification);
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Notification getNotificationById(String id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Notification not found"));
    }

    // Get notifications by customer ID
    public List<Notification> getNotificationsByCustomerId(String customerId) {
        return notificationRepository.findByCustomerId(customerId);
    }

    // Get unread notifications by customer ID
    public List<Notification> getUnreadNotifications(String customerId) {
        return notificationRepository.findByCustomerIdAndReadFalse(customerId);
    }

    // Get notifications by order ID
    public List<Notification> getNotificationsByOrderId(String orderId) {
        return notificationRepository.findByOrderId(orderId);
    }

    // Mark a notification as read
    public Notification markAsRead(String id) {
        Notification notification = getNotificationById(id);

        notification.setRead(true);
        notification.setUpdatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);
    }

    // Delete a notification
    public void deleteNotification(String id) {
        Notification notification = getNotificationById(id);
        notificationRepository.delete(notification);
    }
}
