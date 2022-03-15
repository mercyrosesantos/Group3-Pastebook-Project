package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

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
    private Date reactionTimestamp;

    @Column
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn (name = "userId", nullable = true)
    private User user;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn (name = "postId", nullable = true)
    private Post post;

    @OneToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn (name= "reactionTypeId", nullable = true)
    private ReactionType reactionType;

    // Constructor

    public Reaction() {
    }

    public Reaction(String content, Date reactionTimestamp, boolean isActive) {
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

    public Date getReactionTimestamp() {
        return reactionTimestamp;
    }

    public void setReactionTimestamp(Date reactionTimestamp) {
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

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }

}