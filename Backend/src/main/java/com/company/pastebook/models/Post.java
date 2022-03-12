package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
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
    private Date postTimestamp;

    @Column
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn (name = "userId", nullable = true)
    private User user;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<Reaction> postReactions;

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Reaction> getPostReactions() {
        return postReactions;
    }

    public void setPostReactions(Set<Reaction> postReactions) {
        this.postReactions = postReactions;
    }

    @ManyToOne
    @JoinColumn (name = "timelineUserId", nullable = true)
    private User timelineUser;

    // Constructor

    public Post() {
    }

    public Post(String content, Date postTimestamp, boolean isActive) {
        this.content = content;
        this.postTimestamp = postTimestamp;
        this.isActive = isActive;

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

    public Date getPostTimestamp() {
        return postTimestamp;
    }

    public void setPostTimestamp(Date postTimestamp) {
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

    public User getTimelineUser() {
        return timelineUser;
    }

    public void setTimelineUser(User timelineUser) {
        this.timelineUser = timelineUser;
    }

}