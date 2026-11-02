package com.student_marketplace.app.factory;

import com.student_marketplace.app.domain.Notification;

import java.time.LocalDateTime;

public class NotificationFactory {

    // Builds a Notification from every ERD field (e.g. loading existing data)
    public static Notification createNotification(
            Long notificationId,
            Long userId,
            String type,
            String title,
            String message,
            boolean isRead,
            String link,
            LocalDateTime createdAt
    ) {

        return new Notification(
                notificationId,
                userId,
                type,
                title,
                message,
                isRead,
                link,
                createdAt
        );
    }


    // Builds a brand-new notification for a user:
    // - notificationId is left null (the repository assigns it on save)
    // - isRead starts as false
    // - createdAt is set to now
    public static Notification createNewNotification(
            Long userId,
            String type,
            String title,
            String message,
            String link
    ) {

        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title is required");
        }

        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("message is required");
        }

        return new Notification(
                null,
                userId,
                (type == null || type.isBlank()) ? "SYSTEM" : type,
                title,
                message,
                false,
                link,
                LocalDateTime.now()
        );
    }
}
