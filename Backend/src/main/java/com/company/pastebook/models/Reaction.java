package com.company.pastebook.models;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reactions_seq")
    @SequenceGenerator(name = "reactions_seq", sequenceName = "sequence_reactions", allocationSize = 1)
    private long id;

    @Column
    private String content;

    @Column
    private String reactionTimestamp;

    @Column
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn (name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn (name = "postId", nullable = true)
    private Post post;

    // Constructor


    public Reaction() {
    }

    public Reaction(String content, String reactionTimestamp, boolean isActive) {
        this.content = content;
        this.reactionTimestamp = reactionTimestamp;
        this.isActive = isActive;
    }

    //Getter and Setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReactionTimestamp() {
        return reactionTimestamp;
    }

    public void setReactionTimestamp(String reactionTimestamp) {
        this.reactionTimestamp = reactionTimestamp;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
