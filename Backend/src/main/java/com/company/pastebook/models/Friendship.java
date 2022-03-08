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

    // User Id
    @Column
    private Long userId;

    // Friend Id
    @ManyToOne
    @JoinColumn(name = "friendId", nullable = false)
    private User friend;

    // Constructor

    public Friendship() {
    }

    // Getter and Setter

    public Long getId() {
        return id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
