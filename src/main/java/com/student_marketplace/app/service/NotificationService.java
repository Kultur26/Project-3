package com.student_marketplace.app.service;

import com.student_marketplace.app.domain.Notification;
import com.student_marketplace.app.factory.NotificationFactory;
import com.student_marketplace.app.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;


    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }


    // Rule: each user may receive one or more notifications
    public Notification sendNotification(
            Long userId,
            String type,
            String title,
            String message,
            String link
    ) {

        Notification notification = NotificationFactory.createNewNotification(
                userId, type, title, message, link
        );

        return notificationRepository.save(notification);
    }


    public List<Notification> getNotificationsForUser(Long userId) {

        return notificationRepository.findByUserId(userId);
    }


    public long getUnreadCount(Long userId) {

        return notificationRepository.findByUserId(userId).stream()
                .filter(n -> !n.getIsRead())
                .count();
    }


    // Rule: each user may mark a notification as read.
    // Empty if it doesn't exist OR belongs to another user.
    public Optional<Notification> markAsRead(Long userId, Long notificationId) {

        return findOwned(userId, notificationId)
                .map(notification -> {
                    notification.setIsRead(true);
                    return notificationRepository.save(notification);
                });
    }


    // Rule: each user may delete a notification (hard delete).
    // False if it doesn't exist OR belongs to another user.
    public boolean deleteNotification(Long userId, Long notificationId) {

        return findOwned(userId, notificationId)
                .map(n -> notificationRepository.deleteById(notificationId))
                .orElse(false);
    }


    // Deletes all of one user's notifications ("Clear all")
    public int clearAll(Long userId) {

        return notificationRepository.deleteByUserId(userId);
    }


    private Optional<Notification> findOwned(Long userId, Long notificationId) {

        return notificationRepository.findById(notificationId)
                .filter(n -> n.getUserId().equals(userId));
    }
}
