package com.student_marketplace.app.domain;

import java.time.LocalDateTime;

/*
 * Maps to the Notifications table in the ERD:
 *
 *   notification_id (PK) -> notificationId
 *   user_id (FK)         -> userId
 *   type                 -> type
 *   title                -> title
 *   message              -> message
 *   is_read              -> isRead
 *   link                 -> link
 *   created_at           -> createdAt
 */
public class Notification {

    private Long notificationId;

    private Long userId;

    private String type;

    private String title;

    private String message;

    private boolean isRead;

    private String link;

    private LocalDateTime createdAt;


    // Default constructor
    public Notification() {
    }


    // Constructor
    public Notification(
            Long notificationId,
            Long userId,
            String type,
            String title,
            String message,
            boolean isRead,
            String link,
            LocalDateTime createdAt
    ) {

        this.notificationId = notificationId;
        this.userId = userId;
        this.type = type;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
        this.link = link;
        this.createdAt = createdAt;
    }


    // Getters and Setters

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    // Named getIsRead (not isRead) so the JSON field is "isRead", matching the ERD
    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
