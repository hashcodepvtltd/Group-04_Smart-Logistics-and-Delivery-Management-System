
package com.logiflow.notification_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.Valid;

import com.logiflow.notification_service.model.Notification;
import com.logiflow.notification_service.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Create a notification
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Notification createNotification(@Valid @RequestBody Notification notification) {
        return notificationService.createNotification(notification);
    }

    // Get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable String id) {
        return notificationService.getNotificationById(id);
    }

    // Get notifications by customer ID
    @GetMapping("/customer/{customerId}")
    public List<Notification> getNotificationsByCustomerId(@PathVariable String customerId) {
        return notificationService.getNotificationsByCustomerId(customerId);
    }

    // Get unread notifications by customer ID
    @GetMapping("/customer/{customerId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable String customerId) {
        return notificationService.getUnreadNotifications(customerId);
    }

    // Get notifications by order ID
    @GetMapping("/order/{orderId}")
    public List<Notification> getNotificationsByOrderId(@PathVariable String orderId) {
        return notificationService.getNotificationsByOrderId(orderId);
    }

    // Mark notification as read
    @PatchMapping("/{id}/read")
    public Notification markAsRead(@PathVariable String id) {
        return notificationService.markAsRead(id);
    }

    // Delete notification
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
