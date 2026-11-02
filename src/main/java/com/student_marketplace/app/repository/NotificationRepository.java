package com.student_marketplace.app.repository;

import com.student_marketplace.app.domain.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/*
 * In-memory store (same approach as ProfileRepository).
 * Data is lost when the app restarts.
 */
@Repository
public class NotificationRepository {

    private final List<Notification> notifications = new ArrayList<>();

    private final AtomicLong nextId = new AtomicLong(1);


    public List<Notification> findAll() {

        return new ArrayList<>(notifications);
    }


    public Optional<Notification> findById(Long notificationId) {

        return notifications.stream()
                .filter(n -> n.getNotificationId().equals(notificationId))
                .findFirst();
    }


    // All notifications for one user, newest first
    public List<Notification> findByUserId(Long userId) {

        return notifications.stream()
                .filter(n -> n.getUserId().equals(userId))
                .sorted(Comparator.comparing(Notification::getCreatedAt).reversed())
                .toList();
    }


    // New notification (no id yet) -> assigns the next id.
    // Existing notification -> replaces the stored one.
    public Notification save(Notification notification) {

        if (notification.getNotificationId() == null) {

            notification.setNotificationId(nextId.getAndIncrement());

        } else {

            notifications.removeIf(
                    existing -> existing.getNotificationId()
                            .equals(notification.getNotificationId())
            );
        }

        notifications.add(notification);

        return notification;
    }


    // Hard delete. Returns false if nothing had that id.
    public boolean deleteById(Long notificationId) {

        return notifications.removeIf(
                n -> n.getNotificationId().equals(notificationId)
        );
    }


    // Deletes every notification for a user. Returns how many were removed.
    public int deleteByUserId(Long userId) {

        int before = notifications.size();

        notifications.removeIf(n -> n.getUserId().equals(userId));

        return before - notifications.size();
    }
}
