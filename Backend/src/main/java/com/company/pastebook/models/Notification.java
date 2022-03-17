package com.company.pastebook.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notifications")
public class Notification implements Comparable<Notification> {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notifications_seq")
    @SequenceGenerator(name = "notifications_seq", sequenceName = "sequence_notifications", allocationSize = 1)
    private Long id;

    @Column
    private String notificationType;

    @Column
    private boolean isRead = false;

    @Column
    private LocalDateTime notificationTimestamp;

    @ManyToOne
    @JoinColumn (name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn (name = "friendId", nullable = true)
    private User friend;

    @ManyToOne
    @JoinColumn (name = "postId", nullable = true)
    private Post post;

    // Constructor

    public Notification() {
    }

    public Notification(String notificationType, boolean isRead, LocalDateTime notificationTimestamp) {
        this.notificationType = notificationType;
        this.isRead = isRead;
        this.notificationTimestamp = notificationTimestamp;
    }

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public LocalDateTime getNotificationTimestamp() {
        return notificationTimestamp;
    }

    public void setNotificationTimestamp(LocalDateTime notificationTimestamp) {
        this.notificationTimestamp = notificationTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    // Compare method
    @Override
    public int compareTo (Notification notification) {
        return notificationTimestamp.compareTo(notification.getNotificationTimestamp());
    }
}
