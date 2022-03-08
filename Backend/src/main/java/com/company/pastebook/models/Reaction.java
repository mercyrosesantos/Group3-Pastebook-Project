package com.company.pastebook.models;

import javax.persistence.*;

@Entity
@Table(name = "reactions")
public class Reaction {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reactions_seq")
    @SequenceGenerator(name = "reactions_seq", sequenceName = "sequence_reactions", allocationSize = 1)
    private Long id;

    // Suggest regarding reaction type to include directly instead of new table
    @Column
    private String reactionType;

    @Column
    private String content;

    @Column
    private String reactionTimestamp;

    @Column
    private boolean isActive = true;

    // User id referring to user who reacted (?)
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    // Constructor

    public Reaction() {
    }

    public Reaction(String reactionType, String content) {
        this.reactionType = reactionType;
        this.content = content;
    }

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
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
