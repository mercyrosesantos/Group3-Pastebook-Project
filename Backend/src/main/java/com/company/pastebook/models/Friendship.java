package com.company.pastebook.models;

import javax.persistence.*;

@Entity
@Table(name = "friendships")
public class Friendship {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendships_seq")
    @SequenceGenerator(name = "friendships_seq", sequenceName = "sequence_friendships", allocationSize = 1)
    private Long id;

    @Column
    private String friendshipTimestamp;

    @Column
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private User user;

    private String status;

    // Friend Id
    @ManyToOne
    @JoinColumn(name = "friendId", nullable = true)
    private User friend;

    // Constructor

    public Friendship() {
    }

    public Friendship(String friendshipTimestamp, boolean isActive, String status) {
        this.friendshipTimestamp = friendshipTimestamp;
        this.isActive = isActive;
        this.status = status;
    }

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFriendshipTimestamp() {
        return friendshipTimestamp;
    }

    public void setFriendshipTimestamp(String friendshipTimestamp) {
        this.friendshipTimestamp = friendshipTimestamp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
