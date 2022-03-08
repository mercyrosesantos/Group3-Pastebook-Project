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
}
