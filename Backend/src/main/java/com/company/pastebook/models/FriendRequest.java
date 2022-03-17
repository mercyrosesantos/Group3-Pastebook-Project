package com.company.pastebook.models;

import javax.persistence.*;

@Entity
@Table(name = "friendRequests")
public class FriendRequest {

    // Properties
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friendRequests_seq")
    @SequenceGenerator(name = "friendRequests_seq", sequenceName = "sequence_friendRequests", allocationSize = 1)
    private Long id;

    @Column
    private String requestTimestamp;

    @Column
    private String status = "pending";

    // Requestor Id
    @ManyToOne
    @JoinColumn (name = "requestorId", nullable = true)
    private User requestor;


    // Requestee Id
    @ManyToOne
    @JoinColumn (name = "requesteeId", nullable = true)
    private User requestee;


    // Constructor

    public FriendRequest() {
    }

    public FriendRequest(String requestTimestamp, String status) {
        this.requestTimestamp = requestTimestamp;
        this.status = status;
    }

    // Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(String requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getRequestee() {
        return requestee;
    }

    public void setRequestee(User requestee) {
        this.requestee = requestee;
    }

    public User getRequestor() {
        return requestor;
    }

    public void setRequestor(User requestor) {
        this.requestor = requestor;
    }
}
