package com.company.pastebook.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "album")
public class Album {

    // Properties

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_seq")
    @SequenceGenerator(name = "album_seq", sequenceName = "sequence_album", allocationSize = 1)
    private long id;

    @Column
    private String albumName;

    @Column
    private Date albumTimestamp;

    @Column
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn (name = "userId", nullable = true)
    private User user;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private Set<Photo> albumPhotos;

    //Constructors

    public Album() {
    }

    public Album(String albumName, Date albumTimestamp, boolean isActive) {
        this.albumName = albumName;
        this.albumTimestamp = albumTimestamp;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Date getAlbumTimestamp() {
        return albumTimestamp;
    }

    public void setAlbumTimestamp(Date albumTimestamp) {
        this.albumTimestamp = albumTimestamp;
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

    public Set<Photo> getAlbumPhotos() {
        return albumPhotos;
    }

    public void setAlbumPhotos(Set<Photo> albumPhotos) {
        this.albumPhotos = albumPhotos;
    }
}
