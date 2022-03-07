package com.pointwest.capstone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "sequence_posts", allocationSize = 1)
    private Long id;

    @Column
    private String content;

    @Column
    private String postTimestamp;

    @Column
    private boolean isActive;

    @ManytoOne
    @JoinColumn (referencedColumnName = "id", name = "userId", nullable = false)
    private User user;

    // Constructor

    public Post() {
    }

    public Post(String content) {
        this.content = content;
    }

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostTimestamp() {
        return postTimestamp;
    }

    public void setPostTimestamp(String postTimestamp) {
        this.postTimestamp = postTimestamp;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}