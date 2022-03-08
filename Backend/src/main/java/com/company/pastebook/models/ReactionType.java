package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "reaction_types")
public class ReactionType {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reaction_types_seq")
    @SequenceGenerator(name = "reaction_types_seq", sequenceName = "sequence_reaction_types", allocationSize = 1)
    private long id;

    @Column
    private String reactionTypeName;

//    @OneToOne(mappedBy = "reactions")
//    @JsonIgnore
//    private Set<Reaction> reactions;

    // Constructor
    public ReactionType() {
    }

    public ReactionType(String reactionTypeName) {
        this.reactionTypeName = reactionTypeName;
    }

    // Getter and Setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReactionTypeName() {
        return reactionTypeName;
    }

    public void setReactionTypeName(String reactionTypeName) {
        this.reactionTypeName = reactionTypeName;
    }
}